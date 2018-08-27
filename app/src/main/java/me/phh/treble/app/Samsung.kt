package me.phh.treble.app

import android.content.Context
import android.content.SharedPreferences
import android.os.SystemProperties
import android.preference.PreferenceManager
import android.util.Log
import java.io.File

class Samsung: EntryStartup {
    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        when(key) {
            SamsungSettings.highBrightess -> {
                val value = sp.getBoolean(key, false)
                SystemProperties.set("persist.sys.samsung.full_brightness", value.toString())
            }
        }
    }

    override fun startup(ctxt: Context) {
        if (!SamsungSettings.enabled()) return

        val sp = PreferenceManager.getDefaultSharedPreferences(ctxt)
        sp.registerOnSharedPreferenceChangeListener(spListener)

        //Refresh parameters on boot
        spListener.onSharedPreferenceChanged(sp, SamsungSettings.highBrightess)
    }

    companion object: EntryStartup {
        var self: Samsung? = null
        override fun startup(ctxt: Context) {
            if (!SamsungSettings.enabled()) return
            self = Samsung()
            self!!.startup(ctxt)
        }
    }
}
