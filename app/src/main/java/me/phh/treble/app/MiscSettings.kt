package me.phh.treble.app

import android.os.Bundle
import android.os.SystemProperties
import android.preference.PreferenceFragment

object MiscSettings {
    val mobileSignal = "key_misc_mobile_signal"
    val fpsDivisor = "key_misc_fps_divisor"
    val maxAspectRatioPreO = "key_misc_max_aspect_ratio_pre_o"
    val multiCameras = "key_misc_multi_camera"

    fun enabled(): Boolean = true
}

class MiscSettingsFragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_misc)
    }
}
