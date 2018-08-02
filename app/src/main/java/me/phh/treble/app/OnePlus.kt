package me.phh.treble.app

import android.content.Context
import android.content.SharedPreferences
import android.media.AudioManager
import android.os.SystemProperties
import android.os.UEventObserver
import android.preference.PreferenceManager
import android.util.Log

object OnePlus: EntryStartup {
    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        when(key) {
            OnePlusSettings.displayModeKey -> {
                val value = sp.getString(key, "default")
                when (value) {
                    "default" -> {
                        SystemProperties.set("sys.dci3p", "0")
                        SystemProperties.set("sys.dcip3", "0")
                        SystemProperties.set("sys.srgb", "0")
                        SystemProperties.set("sys.default_mode", "1")
                    }
                    "srgb" -> {
                        SystemProperties.set("sys.dci3p", "0")
                        SystemProperties.set("sys.dcip3", "0")
                        SystemProperties.set("sys.srgb", "1")
                        SystemProperties.set("sys.default_mode", "0")
                    }
                    "dcip3" -> {
                        SystemProperties.set("sys.dci3p", "1")
                        SystemProperties.set("sys.dcip3", "1")
                        SystemProperties.set("sys.srgb", "0")
                        SystemProperties.set("sys.default_mode", "0")
                    }
                }
            }
            OnePlusSettings.usbOtgKey -> {
                val value = sp.getBoolean(key, false)
                SystemProperties.set("persist.sys.oem.otg_support", value.toString())
            }
            OnePlusSettings.highBrightnessModeKey -> {
                val value = sp.getString(key, "0")
                SystemProperties.set("sys.hbm", value)
            }
        }
    }

    override fun startup(ctxt: Context) {
        if(!OnePlusSettings.enabled()) return
        Log.d("PHH", "Starting OP6 service")
        object : UEventObserver() {
            override fun onUEvent(event: UEventObserver.UEvent) {
                try {
                    android.util.Log.v("PHH", "USB UEVENT: " + event.toString())
                    val state = event.get("STATE")

                    val ringing = state.contains("USB=0")
                    val silent = state.contains("(null)=0")
                    val vibrate = state.contains("USB_HOST=0")
                    android.util.Log.v("PHH", "Got ringing = $ringing, silent = $silent, vibrate = $vibrate")
                    if (ringing && !silent && !vibrate)
                        Tools.audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL)
                    if (silent && !ringing && !vibrate)
                        Tools.audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT)
                    if (vibrate && !silent && !ringing)
                        Tools.audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE)
                } catch (e: Exception) {
                    android.util.Log.d("PHH", "Failed parsing uevent", e)
                }

            }
        }.startObserving("DEVPATH=/devices/platform/soc/soc:tri_state_key")

        val sp = PreferenceManager.getDefaultSharedPreferences(ctxt)
        sp.registerOnSharedPreferenceChangeListener(spListener)

        //Refresh parameters on boot
        spListener.onSharedPreferenceChanged(sp, OnePlusSettings.displayModeKey)
        spListener.onSharedPreferenceChanged(sp, OnePlusSettings.usbOtgKey)
        spListener.onSharedPreferenceChanged(sp, OnePlusSettings.highBrightnessModeKey)
    }
}