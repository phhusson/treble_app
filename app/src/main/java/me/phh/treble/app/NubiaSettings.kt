package me.phh.treble.app

import android.util.Log

object NubiaSettings : Settings {
    val dt2w = "nubia_double_tap_to_wake"
    val tsGameMode = "nubia_touchscreen_game_mode"
    val fanSpeed = "nubia_fan_speed"
    val logoBreath = "nubia_redmagic_logo_breath"
    val redmagicLed = "nubia_redmagic_led"
    val boostCpu = "nubia_boost_cpu"
    val boostGpu = "nubia_boost_gpu"
    val boostCache = "nubia_boost_cache"

    override fun enabled() = Tools.vendorFp.toLowerCase().startsWith("nubia/")
}

class NubiaSettingsFragment : SettingsFragment() {
    override val preferencesResId = R.xml.pref_nubia
}
