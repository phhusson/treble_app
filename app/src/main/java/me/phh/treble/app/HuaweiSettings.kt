package me.phh.treble.app

import android.os.Bundle
import android.os.SystemProperties
import android.preference.PreferenceFragment

object HuaweiSettings {
    val fingerprintGestures = "key_huawei_fingerprint_gestures"
    val touchscreenGloveMode = "key_huawei_touchscreen_glove_mode"
    val fastCharge = "key_huawei_fast_charge"
    val noHwcomposer = "key_huawei_no_hwcomposer"

    fun enabled(): Boolean =
            Tools.vendorFpLow.contains("huawei") ||
            Tools.vendorFpLow.contains("honor") ||
            SystemProperties.getBoolean("persist.sys.overlay.huawei", false)
}

class HuaweiSettingsFragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_huawei)
    }
}