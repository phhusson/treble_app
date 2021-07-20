package me.phh.treble.app

import android.app.Activity
import android.app.ActivityOptions
import android.app.AlertDialog
import android.content.*
import android.hardware.display.DisplayManager
import android.hardware.input.InputManager
import android.media.AudioManager
import android.os.*
import android.util.Log
import android.util.SparseArray
import android.view.InputDevice
import android.view.SurfaceControl
import android.view.WindowManager
import android.view.WindowManagerGlobal
import java.io.ByteArrayOutputStream
import java.io.Closeable
import java.io.File
import java.io.FileInputStream
import kotlin.concurrent.thread

object Razr: EntryStartup {

    val handler = Handler(HandlerThread("Razr").also { it.start() }.looper)

    override fun startup(ctxt: Context) {
        val im = ctxt.getSystemService(InputManager::class.java)!!
        val dm = ctxt.getSystemService(DisplayManager::class.java)!!
        val wm = ctxt.getSystemService(WindowManager::class.java)!!
        val wms = WindowManagerGlobal.getWindowManagerService()
        val lightsManager = ctxt.getSystemService("lights")!!
        val powerManager = ctxt.getSystemService(PowerManager::class.java)!!

        if(!Tools.vendorFp.toLowerCase().startsWith("motorola/olson_")) return

        // XXX: This assumes secondary screen is [1]
        val secondaryDisplayId = dm.displays[1].displayId

        // Start by fixing the association between touchscreen and the secondary screen
        Log.d("PHH", "Setting Moto Razr secondary display touchscreen association on screen $secondaryDisplayId")
        val m = InputManager::class.java.getMethod("addPortAssociation", String::class.java, Int::class.java)
        m.invoke(im, "synaptics_dsx_i2c/input.1", 130)

        // Now tell WM that the secondary display is meant for use by system only

        // No IME
        wms.javaClass.getMethod("setShouldShowIme", Int::class.java, Boolean::class.java)
                .invoke(wms, secondaryDisplayId, false)
        // No navbar, launcher, status bar
        wms.javaClass.getMethod("setShouldShowSystemDecors", Int::class.java, Boolean::class.java)
                .invoke(wms, secondaryDisplayId, false)
        // Display stuff even if on (insecure) keyguard
        wms.javaClass.getMethod("setShouldShowWithInsecureKeyguard", Int::class.java, Boolean::class.java)
                .invoke(wms, secondaryDisplayId, true)
        // If display is destroyed (uh...?) destroy its activities
        wms.javaClass.getMethod("setRemoveContentMode", Int::class.java, Int::class.java)
                .invoke(wms, secondaryDisplayId, 2 /* REMOVE_CONTENT_MODE_DESTROY */)
        // Any activity displayed on that screen is to be fullscreen
        wms.javaClass.getMethod("setWindowingMode", Int::class.java, Int::class.java)
                .invoke(wms, secondaryDisplayId, 1 /* WINDOWING_MODE_FULLSCREEN  */)

        val sfDisplayIds = SurfaceControl::class.java.getMethod("getPhysicalDisplayIds").invoke(null) as LongArray
        for(sfId in sfDisplayIds) {
            Log.d("PHH", "Got sf display id $sfId")
        }

        // XXX: This assumes secondary screen is [1]
        val displayToken =
                SurfaceControl::class.java.getMethod("getPhysicalDisplayToken", Long::class.java)
                        .invoke(null, sfDisplayIds[1])
        val primaryDisplayToken =
                SurfaceControl::class.java.getMethod("getPhysicalDisplayToken", Long::class.java)
                        .invoke(null, sfDisplayIds[0])

        val i = Intent().setComponent(ComponentName("me.phh.treble.app", "me.phh.treble.app.RazrSecondary"))
        val options = ActivityOptions.makeBasic()
        options.launchDisplayId = secondaryDisplayId
        val m2 = Context::class.java.getMethod("startActivityAsUser", Intent::class.java, Bundle::class.java, UserHandle::class.java)
        m2.invoke(ctxt, i, options.toBundle(), UserHandle.getUserHandleForUid(10105))

        // settings put global lid_behavior 0

        var closed = false
        var ongoingTransition = false
        val setDisplayPowerModeMethod = SurfaceControl::class.java.getMethod("setDisplayPowerMode", IBinder::class.java, Int::class.java)
        val screenStateReceiver = object: BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Log.d("PHH", "Received intent ${intent.action} ${intent.extras?.keySet()}")
                if(intent.action == Intent.ACTION_SCREEN_ON  || intent.action == Intent.ACTION_SCREEN_OFF) {
                    ongoingTransition = true
                    if(closed) {
                        handler.postDelayed({
                            if(!ongoingTransition) return@postDelayed
                            setDisplayPowerModeMethod.invoke(null, displayToken, 2)
                            setDisplayPowerModeMethod.invoke(null, primaryDisplayToken, 0)
                            ongoingTransition = false
                        }, 100L)
                    } else {
                        handler.postDelayed({
                            if(!ongoingTransition) return@postDelayed
                            setDisplayPowerModeMethod.invoke(null, displayToken, 0)
                            setDisplayPowerModeMethod.invoke(null, primaryDisplayToken, 2)
                            ongoingTransition = false
                        }, 100L)
                    }
                }
            }
        }

        object : UEventObserver() {
            override fun onUEvent(event: UEventObserver.UEvent) {
                try {
                    Log.v("PHH", "Moto Razr Flip UEVENT: " + event.toString())

                    val state = event.get("STATE")

                    closed = state.contains("MECHANICAL=1")
                    Log.v("PHH", "Closed = $closed")

                    if(closed) {
                        handler.post {
                            PowerManager::class.java.getMethod("goToSleep", Long::class.java).invoke(powerManager, SystemClock.uptimeMillis())
                            setDisplayPowerModeMethod.invoke(null, displayToken, 2)
                            setDisplayPowerModeMethod.invoke(null, primaryDisplayToken, 0)
                            ongoingTransition = false
                        }
                    } else {
                        handler.post {
                            PowerManager::class.java.getMethod("wakeUp", Long::class.java).invoke(powerManager, SystemClock.uptimeMillis())
                            setDisplayPowerModeMethod.invoke(null, displayToken, 0)
                            setDisplayPowerModeMethod.invoke(null, primaryDisplayToken, 2)
                            ongoingTransition = false
                        }
                    }
                } catch (e: Exception) {
                    Log.d("PHH", "Failed parsing uevent", e)
                }

            }
        }.startObserving("/devices/platform/soc/soc:flip_detection")

        val screenStateFilter = IntentFilter()
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF)
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON)
        ctxt.registerReceiver(screenStateReceiver, screenStateFilter)
    }
}

class RazrSecondary : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setShowWhenLocked(true)

        /*val params = window.attributes
        params.flags =
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                        WindowManager.LayoutParams.FLAG_FULLSCREEN
        params.type = 2020
        window.attributes = params*/
    }

    override fun onResume() {
        super.onResume()
        Log.d("PHH", "Setting window type to 2006")
    }
}
