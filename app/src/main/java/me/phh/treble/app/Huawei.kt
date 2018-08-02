package me.phh.treble.app

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import vendor.huawei.hardware.biometrics.fingerprint.V2_1.IExtBiometricsFingerprint
import vendor.huawei.hardware.tp.V1_0.ITouchscreen

class Huawei: EntryStartup {
    val fpService = IExtBiometricsFingerprint.getService()
    val tsService = ITouchscreen.getService()

    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        when(key) {
            HuaweiSettings.fingerprintGestures -> {
                val value = sp.getBoolean(key, true)
                Log.d("PHH", "Setting Huawei fingerprint gestures to $value")
                if(value)
                    fpService.sendCmdToHal(41)
                else
                    fpService.sendCmdToHal(42)
            }
            HuaweiSettings.touchscreenGloveMode -> {
                val value = sp.getBoolean(key, false)
                Log.d("PHH", "Setting Huawei glove mode to $value")
                tsService.hwTsSetGloveMode(value)
            }
        }
    }

    override fun startup(ctxt: Context) {
        if (!HuaweiSettings.enabled()) return

        val sp = PreferenceManager.getDefaultSharedPreferences(ctxt)
        sp.registerOnSharedPreferenceChangeListener(spListener)

        //Refresh parameters on boot
        spListener.onSharedPreferenceChanged(sp, HuaweiSettings.fingerprintGestures)
        spListener.onSharedPreferenceChanged(sp, HuaweiSettings.touchscreenGloveMode)
        tsService.hwTsGetChipInfo({ _, chip_info ->
            Log.d("PHH", "HW Touchscreen chip $chip_info")
        })
    }

    companion object: EntryStartup {
        var self: Huawei? = null
        override fun startup(ctxt: Context) {
            if (!HuaweiSettings.enabled()) return
            self = Huawei()
            self!!.startup(ctxt)
        }
    }
}