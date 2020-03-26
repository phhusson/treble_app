package me.phh.treble.app

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object Xiaomi : EntryStartup {
    fun setDt2w(enable: Boolean) {
        val value = if(enable) "1" else "0"
        Misc.safeSetprop("persist.sys.phh.xiaomi.dt2w", value)
    }

    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        when (key) {
            XiaomiSettings.dt2w -> {
                //TODO: We need to check that the screen is on at this time
                //This won't have any effect if done with screen off
                val b = sp.getBoolean(key, false)
                setDt2w(b)
            }
        }
    }

    override fun startup(ctxt: Context) {
        if (!XiaomiSettings.enabled()) return


        val sp = PreferenceManager.getDefaultSharedPreferences(ctxt)
        sp.registerOnSharedPreferenceChangeListener(spListener)

        //Refresh parameters on boot
        spListener.onSharedPreferenceChanged(sp, XiaomiSettings.dt2w)
    }
}
