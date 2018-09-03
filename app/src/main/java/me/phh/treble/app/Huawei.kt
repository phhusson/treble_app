package me.phh.treble.app

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import vendor.huawei.hardware.biometrics.fingerprint.V2_1.IExtBiometricsFingerprint
import vendor.huawei.hardware.tp.V1_0.ITouchscreen
import java.io.File

class Huawei: EntryStartup {
    val fastChargeCharger = "/sys/class/hw_power/charger/direct_charger/iin_thermal"
    val fastChargeData = "/sys/class/hw_power/charger/charge_data/iin_thermal"
    val fpService = IExtBiometricsFingerprint.getService()
    val tsService = ITouchscreen.getService()

    fun writeToFileNofail(path: String, content: String) {
        try {
            File(path).printWriter().use { it.println(content) }
        } catch(t: Throwable) {
            Log.d("PHH", "Failed writing to $path", t)
        }
    }

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
            HuaweiSettings.fastCharge -> {
                val value = sp.getString(key, "-1")
                when(value) {
                    "-1" -> {
                        writeToFileNofail(fastChargeCharger, "1500")
                        writeToFileNofail(fastChargeData, "900")
                    }
                    "2750" -> {
                        writeToFileNofail(fastChargeCharger, "2750")
                        writeToFileNofail(fastChargeData, "1000")
                    }
                    "4000" -> {
                        writeToFileNofail(fastChargeCharger, "4000")
                        writeToFileNofail(fastChargeData, "2000")
                    }
                }
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
        spListener.onSharedPreferenceChanged(sp, HuaweiSettings.fastCharge)
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