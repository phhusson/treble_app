package me.phh.treble.app

import android.os.Bundle
import android.preference.PreferenceFragment
import java.io.File

object LenovoSettings {
    val dt2w = "lenovo_double_tap_to_wake"

    fun enabled(): Boolean = Tools.vendorFp.contains("Lenovo") && File(Lenovo.dtPanel).exists()
}

class LenovoSettingsFragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_lenovo)
    }
}
