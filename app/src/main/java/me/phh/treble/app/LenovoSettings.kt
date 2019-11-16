package me.phh.treble.app

import java.io.File

object LenovoSettings : Settings {
    val dt2w = "lenovo_double_tap_to_wake"

    override fun enabled() = Tools.vendorFp.contains("Lenovo") && File(Lenovo.dtPanel).exists()
}

class LenovoSettingsFragment : SettingsFragment() {
    override val preferencesResId = R.xml.pref_lenovo
}
