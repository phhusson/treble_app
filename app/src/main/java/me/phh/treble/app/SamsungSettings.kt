package me.phh.treble.app

object SamsungSettings : Settings {
    val highBrightess = "key_samsung_high_brightness"
    val gloveMode = "key_samsung_glove_mode"
    val audioStereoMode = "key_samsung_audio_stereo"
    val wirelessChargingTransmit = "key_samsung_wireless_charging_transmit"
    val doubleTapToWake = "key_samsung_double_tap_to_wake"

    override fun enabled() = Tools.vendorFpLow.startsWith("samsung/")
}

class SamsungSettingsFragment : SettingsFragment() {
    override val preferencesResId = R.xml.pref_samsung
}
