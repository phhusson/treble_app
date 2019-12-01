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
    fun setDt2w(enable: Boolean) {
        val dtPanel = setOf(
                "/proc/touchpanel/wakeup_gesture", //Daisy custom
                "/proc/tp_wakeup_gesture") //Daisy stock
                .firstOrNull { File(it).exists() }

        val value = if(enable) "1" else "0"
        if(dtPanel != null) {
            writeToFileNofail(dtPanel, value)
        }

        val evNode = SystemProperties.get("persist.sys.phh.dt2w_evnode", null)
        if(evNode != null) {
            Log.d("PHH", "Trying to set dt2w mode $evNode")
            try {
                File(evNode).outputStream().use {
                    //\x00\x00\x00\x00 \x00\x00\x00\x00 \x00\x00\x00\x00 \x00\x00\x00\x00 \x00\x00 \x01\x00 \x05\x00\x00\x00
                    val msg = byteArrayOf(
                            0x00, 0x00, 0x00, 0x00,
                            0x00, 0x00, 0x00, 0x00,
                            0x00, 0x00, 0x00, 0x00,
                            0x00, 0x00, 0x00, 0x00,
                            0x00, 0x00, 0x01, 0x00,
                            if (enable) 0x05 else 0x04, 0x00, 0x00, 0x00
                    )
                    it.write(msg)
                }
            } catch(t: Throwable) {
                Log.d("PHH", "Failed setting dt2w mode $evNode", t)
            }
        }
    }

    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        when(key) {
            XiaomiSettings.dt2w -> {
                //TODO: We need to check that the screen is on at this time
                //This won't have any effect if done with screen off
                val b = sp.getBoolean(key, false)
                setDt2w(b)
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
