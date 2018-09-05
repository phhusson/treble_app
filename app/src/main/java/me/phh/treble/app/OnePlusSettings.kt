package me.phh.treble.app

import android.os.Bundle
import android.preference.PreferenceFragment

object OnePlusSettings {
    val displayModeKey = "key_oneplus_display_mode"
    val highBrightnessModeKey = "key_oneplus_display_high_brightness"
    val usbOtgKey = "key_oneplus_usb_otg"
    val dt2w = "key_oneplus_double_tap_to_wake"

    fun enabled(): Boolean = Tools.vendorFp.contains("OnePlus")
}

class OnePlusSettingsFragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_oneplus)
        SettingsActivity.bindPreferenceSummaryToValue(findPreference(OnePlusSettings.displayModeKey))
        SettingsActivity.bindPreferenceSummaryToValue(findPreference(OnePlusSettings.highBrightnessModeKey))
    }
}
