package me.phh.treble.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import java.lang.ref.WeakReference

import vendor.xiaomi.hardware.displayfeature.V1_0.IDisplayFeature

object Xiaomi : EntryStartup {
    fun setDt2w(enable: Boolean) {
        val value = if(enable) "1" else "0"
        Misc.safeSetprop("persist.sys.phh.xiaomi.dt2w", value)
    }

    fun setSunlightModeDisabled(disable: Boolean, c: Context) {
        Log.d("PHH", "Setting Sunlight Mode status to $disable")
        if (disable) {
            Log.d("PHH", "Starting XiaomiSunlightModeDisabler service")
            XiaomiSunlightModeDisabler.startup(c)
        } else {
            Log.d("PHH", "Stopping XiaomiSunlightModeDisabler service")
            XiaomiSunlightModeDisabler.shutdown(c)
        }
    }

    lateinit var ctxt: WeakReference<Context>
    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        val c = ctxt.get()
        if (c == null) return@OnSharedPreferenceChangeListener
        when (key) {
            XiaomiSettings.dt2w -> {
                val b = sp.getBoolean(key, false)
                setDt2w(b)
            }
            XiaomiSettings.disableSunlightMode -> {
                val b = sp.getBoolean(key, false)
                setSunlightModeDisabled(b, c)
            }
        }
    }

    override fun startup(ctxt: Context) {
        if (!XiaomiSettings.enabled()) return

        val sp = PreferenceManager.getDefaultSharedPreferences(ctxt)
        sp.registerOnSharedPreferenceChangeListener(spListener)

        this.ctxt = WeakReference(ctxt.applicationContext)

        //Refresh parameters on boot
        spListener.onSharedPreferenceChanged(sp, XiaomiSettings.dt2w)
        spListener.onSharedPreferenceChanged(sp, XiaomiSettings.disableSunlightMode)
    }
}

class XiaomiSunlightModeDisabler: EntryStartup, BroadcastReceiver() {
    val dfService = try { IDisplayFeature.getService() } catch(t: Throwable) { Log.d("PHH", "Failed getting IDisplayFeature service", t); null }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_SCREEN_ON)
            return
        Log.d("PHH", "Starting XiaomiSunlightModeDisabler service")
        when(intent.action) {
            Intent.ACTION_SCREEN_ON -> {
                try {
                    if (dfService != null) {
                        dfService.setFeature(0, 1, 2, 255)
                    }
                } catch(t: Throwable) {
                    Log.d("PHH", "Failed using IDisplayFeature service", t);
                }
            }
        }
    }

    override fun startup(ctxt: Context) {
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        ctxt.registerReceiver(this, filter)
        Log.d(tag, "Registered for XiaomiSunlightModeDisabler service")
    }

    fun shutdown(ctxt: Context) {
        ctxt.unregisterReceiver(this)
        Log.d(tag, "Unregistered for XiaomiSunlightModeDisabler service")
    }

    companion object : EntryStartup {
        const val tag = "XiaomiSunlightModeDisabler"
        private var self: XiaomiSunlightModeDisabler? = null
        override fun startup(ctxt: Context) {
            self = XiaomiSunlightModeDisabler()
            self!!.startup(ctxt)
        }
        fun shutdown(ctxt: Context) {
            self?.shutdown(ctxt)
        }
    }
}
