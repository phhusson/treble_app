package me.phh.treble.app

object DozeSettings : Settings {
    val handwaveKey = "key_doze_handwave"
    val pocketKey = "key_doze_pocket"

    override fun enabled(): Boolean {
        //TODO: Check if sensors are available and respond to interrupts
        return true
    }
}

class DozeSettingsFragment : SettingsFragment() {
    override val preferencesResId = R.xml.pref_doze
}
