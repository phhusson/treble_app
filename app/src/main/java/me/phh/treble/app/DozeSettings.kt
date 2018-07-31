package me.phh.treble.app

import android.os.Bundle
import android.preference.PreferenceFragment

object DozeSettings {
    val handwaveKey = "key_doze_handwave"
    val pocketKey = "key_doze_pocket"

    fun enabled(): Boolean {
        //TODO: Check if sensors are available and respond to interrupts
        return true
    }
}

class DozeSettingsFragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_doze)
    }
}