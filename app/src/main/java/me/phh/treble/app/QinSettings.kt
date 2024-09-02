package me.phh.treble.app

import android.os.Bundle

object QinSettings : Settings {
    val dt2w = "key_qin_dt2w"

    override fun enabled() = Tools.vendorFp.contains("s9863a1h10_Natv")
}

class QinSettingsFragment : SettingsFragment() {
    override val preferencesResId = R.xml.pref_qin

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        super.onCreatePreferences(savedInstanceState, rootKey)
        android.util.Log.d("PHH", "Loading QIN fragment ${QinSettings.enabled()}")
    }
}
