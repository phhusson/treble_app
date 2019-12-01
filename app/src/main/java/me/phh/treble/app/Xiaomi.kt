package me.phh.treble.app

import android.content.Context
import android.content.SharedPreferences
import android.media.AudioManager
import android.os.SystemProperties
import android.os.UEventObserver
import android.preference.PreferenceManager
import android.util.Log
import java.io.File

object Xiaomi: EntryStartup {
    val dtPanel = "/proc/touchpanel/wakeup_gesture" //DAISYCUSTOM
    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        when(key) {
            XiaomiSettings.dt2w -> {
                var file = File("/proc/tp_wakeup_gesture")
                var fileExists = file.exists()

                if(fileExists){
                    val dtPanel = "/proc/tp_wakeup_gesture" //DAISYSTOCK
                }

                //TODO: We need to check that the screen is on at this time
                //This won't have any effect if done with screen off
                val b = sp.getBoolean(key, false)
                val value = if(b) "1" else "0"
                writeToFileNofail(dtPanel, value)
            }
        }
    }

    fun writeToFileNofail(path: String, content: String) {
        try {
            File(path).printWriter().use { it.println(content) }
        } catch(t: Throwable) {
            Log.d("PHH", "Failed writing to $path", t)
        }
    }

    override fun startup(ctxt: Context) {
        if(!XiaomiSettings.enabled()) return

        val sp = PreferenceManager.getDefaultSharedPreferences(ctxt)
        sp.registerOnSharedPreferenceChangeListener(spListener)

        //Refresh parameters on boot
        spListener.onSharedPreferenceChanged(sp, XiaomiSettings.dt2w)
    }
}
