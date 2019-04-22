package me.phh.treble.app

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.UserHandle
import android.util.Log
import dalvik.system.PathClassLoader

class EntryService: Service() {
    companion object {
        var service: EntryService? = null
    }
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun tryC(fnc: () -> Unit) {
        try {
            fnc()
        } catch(e: Throwable) {
            Log.e("PHH", "Caught", e)
        }
    }

    override fun onCreate() {
        service = this

        tryC { Tools.startup(this) }
        tryC { QtiAudio.startup(this) }
        tryC { OnePlus.startup(this) }
        tryC { OverlayPicker.startup(this) }
        tryC { Doze.startup(this) }
        tryC { Huawei.startup(this) }
        tryC { Misc.startup(this) }
        tryC { Samsung.startup(this) }
        tryC { Hostapd.startup(this) }
    }
}

class Starter: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val caller = UserHandle.getCallingUserId()
        if(caller != 0) {
            Log.d("PHH", "Service called from user none 0, ignore")
            return
        }
        Log.d("PHH", "Starting service")
        //TODO: Check current user == "admin" == 0
        when(intent.action) {
            Intent.ACTION_BOOT_COMPLETED, Intent.ACTION_MY_PACKAGE_REPLACED -> {
                context.startServiceAsUser(Intent(context, EntryService::class.java), UserHandle.SYSTEM)
            }
            "me.phh.update" -> {
                UpdateApplier.applyUpdate(intent.data.path)
            }
        }
    }
}

interface EntryStartup {
    fun startup(ctxt: Context)
}
