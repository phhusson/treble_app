package me.phh.treble.app

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import android.os.SystemProperties
import android.util.Log

import java.io.File

object Nubia : EntryStartup {
    fun writeToFileNofail(path: String, content: String) {
        try {
            File(path).printWriter().use { it.println(content) }
        } catch(t: Throwable) {
            Log.d("PHH", "Failed writing to $path", t)
        }
    }

    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        when (key) {
            NubiaSettings.dt2w -> {
                val b = sp.getBoolean(key, false)
                writeToFileNofail("/data/vendor/tp/easy_wakeup_gesture", if(b) "1" else "0")
            }
            NubiaSettings.tsGameMode -> {
                val b = sp.getBoolean(key, false)
                SystemProperties.set("sys.nubia.touch.game", if(b) "1" else "0")
            }
            NubiaSettings.fanSpeed -> {
                val i = sp.getString(key, "0")
                writeToFileNofail("/sys/kernel/fan/fan_speed_level", i)
            }
        }
    }

    override fun startup(ctxt: Context) {
        if (!NubiaSettings.enabled()) return

        val sp = PreferenceManager.getDefaultSharedPreferences(ctxt)
        sp.registerOnSharedPreferenceChangeListener(spListener)

        //Refresh parameters on boot
        spListener.onSharedPreferenceChanged(sp, NubiaSettings.dt2w)
        spListener.onSharedPreferenceChanged(sp, NubiaSettings.tsGameMode)
        spListener.onSharedPreferenceChanged(sp, NubiaSettings.fanSpeed)
    }
}
