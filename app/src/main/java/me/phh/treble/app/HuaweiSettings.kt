package me.phh.treble.app

import android.hidl.manager.V1_1.IServiceManager
import android.os.Bundle
import android.os.SystemProperties
import android.preference.PreferenceFragment

object HuaweiSettings {
    val fingerprintGestures = "key_huawei_fingerprint_gestures"
    val touchscreenGloveMode = "key_huawei_touchscreen_glove_mode"

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