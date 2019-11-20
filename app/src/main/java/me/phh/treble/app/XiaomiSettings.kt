package me.phh.treble.app

import java.io.File

object XiaomiSettings : Settings {
    val dt2w = "xiaomi_double_tap_to_wake"

    override fun enabled() = Tools.vendorFp.contains("xiaomi") && File(Xiaomi.dtPanel).exists()
}

class XiaomiSettingsFragment : SettingsFragment() {
    override val preferencesResId = R.xml.pref_xiaomi
}
