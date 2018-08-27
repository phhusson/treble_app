package me.phh.treble.app

import android.content.Context
import android.content.SharedPreferences
import android.os.SystemProperties
import android.preference.PreferenceManager
import android.provider.Settings
import android.util.Log
import vendor.huawei.hardware.biometrics.fingerprint.V2_1.IExtBiometricsFingerprint
import vendor.huawei.hardware.tp.V1_0.ITouchscreen

object Misc: EntryStartup {
    override fun startup(ctxt: Context) {
        val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
            when(key) {
                MiscSettings.mobileSignal -> {
                    val value = sp.getString(key, "default")
                    SystemProperties.set("persist.sys.signal.level", value)
                    Log.d("PHH", "Setting signal level method to $value")
                }
                MiscSettings.fpsDivisor -> {
                    val value = sp.getString(key, "1")
                    Log.d("PHH", "Setting fps divisor to $value")
                    Settings.Global.putString(ctxt.applicationContext.contentResolver, "fps_divisor", value)
                }
            }
        }

        if (!MiscSettings.enabled()) return

        val sp = PreferenceManager.getDefaultSharedPreferences(ctxt)
        sp.registerOnSharedPreferenceChangeListener(spListener)

        //Refresh parameters on boot
        spListener.onSharedPreferenceChanged(sp, MiscSettings.fpsDivisor)
        spListener.onSharedPreferenceChanged(sp, MiscSettings.mobileSignal)
    }
}