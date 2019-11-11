package me.phh.treble.app

object MiscSettings : Settings {
    val mobileSignal = "key_misc_mobile_signal"
    val fpsDivisor = "key_misc_fps_divisor"
    val maxAspectRatioPreO = "key_misc_max_aspect_ratio_pre_o"
    val multiCameras = "key_misc_multi_camera"
    val headsetFix = "key_huawei_headset_fix"
    val roundedCorners = "key_misc_rounded_corners"
    val roundedCornersOverlay = "key_misc_rounded_corners_overlay"
    val linearBrightness = "key_misc_linear_brightness"
    val disableButtonsBacklight = "key_misc_disable_buttons_backlight"
    val forceNavbar = "key_misc_force_navbar"

    override fun enabled() = true
}

class MiscSettingsFragment : SettingsFragment() {
    override val preferencesResId = R.xml.pref_misc
}
