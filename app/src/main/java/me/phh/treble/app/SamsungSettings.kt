package me.phh.treble.app

import android.os.Bundle
import android.os.SystemProperties
import android.preference.PreferenceFragment

object SamsungSettings {
    val highBrightess = "key_samsung_high_brightness"
    val gloveMode = "key_samsung_glove_mode"
    val audioStereoMode = "key_samsung_audio_stereo"
    val wirelessChargingTransmit = "key_samsung_wireless_charging_transmit"
    val doubleTapToWake = "key_samsung_double_tap_to_wake"

    fun enabled(): Boolean =
            Tools.vendorFpLow.startsWith("samsung/")
}

class SamsungSettingsFragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_samsung)
    }
}
