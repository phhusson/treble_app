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
            NubiaSettings.logoBreath -> {
                val b = sp.getBoolean(key, false)
                if(b) {
                    writeToFileNofail("/sys/class/leds/blue/breath_feature", "3 1000 0 700 0 255 3")
                } else {
                    writeToFileNofail("/sys/class/leds/blue/breath_feature", "0")
                }
            }
            NubiaSettings.redmagicLed -> {
                val i = sp.getString(key, "0")
                writeToFileNofail("/sys/class/leds/aw22xxx_led/imax", "8")
                writeToFileNofail("/sys/class/leds/aw22xxx_led/effect", i)
                writeToFileNofail("/sys/class/leds/aw22xxx_led/cfg", "1")
            }
            NubiaSettings.boostCpu -> {
                val b = sp.getBoolean(key, false)
                SystemProperties.set("persist.sys.cpu.boost", if(b) "1" else "0")
            }
            NubiaSettings.boostGpu -> {
                val b = sp.getBoolean(key, false)
                SystemProperties.set("persist.sys.gpu.boost", if(b) "1" else "0")
            }
            NubiaSettings.boostCache -> {
                val b = sp.getBoolean(key, false)
                SystemProperties.set("persist.sys.cache.boost", if(b) "1" else "0")
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
        spListener.onSharedPreferenceChanged(sp, NubiaSettings.logoBreath)
        spListener.onSharedPreferenceChanged(sp, NubiaSettings.redmagicLed)
    }
}
