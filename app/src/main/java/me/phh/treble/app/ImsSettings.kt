package me.phh.treble.app

import android.app.AlertDialog
import android.app.Application
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.util.Log
import androidx.preference.ListPreference
import androidx.preference.Preference

object ImsSettings : Settings {
    val requestNetwork = "key_ims_request_network"
    val createApn = "key_ims_create_apn"
    val forceEnableSettings = "key_ims_force_enable_setting"

    override fun enabled() = true
}

class ImsSettingsFragment : SettingsFragment() {
    override val preferencesResId = R.xml.pref_ims
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        super.onCreatePreferences(savedInstanceState, rootKey)

        val createApn = findPreference<Preference>(ImsSettings.createApn)
        createApn!!.setOnPreferenceClickListener {
            return@setOnPreferenceClickListener true
        }
    }
}
