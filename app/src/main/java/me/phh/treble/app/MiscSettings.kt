package me.phh.treble.app

import android.os.Bundle
import androidx.preference.Preference

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
    val bluetooth = "key_misc_bluetooth"
    val securize = "key_misc_securize"

    override fun enabled() = true
}

class MiscSettingsFragment : SettingsFragment() {
    override val preferencesResId = R.xml.pref_misc
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        super.onCreatePreferences(savedInstanceState, rootKey)

        val pref = findPreference<Preference>(MiscSettings.securize)
        pref!!.setOnPreferenceClickListener {
            var cmds = listOf(
                    "/sbin/su 0 /system/bin/phh-securize.sh",
                    "/sbin/su -c /system/bin/phh-securize.sh",
                    "/system/xbin/phh-su 0 /system/bin/phh-securize.sh",
                    "/system/xbin/phh-su -c /system/bin/phh-securize.sh",
                    "/system/xbin/su 0 /system/bin/phh-securize.sh",
                    "/system/xbin/su -c /system/bin/phh-securize.sh"
            )
            for(cmd in cmds) {
                try {
                    Runtime.getRuntime().exec(cmd).waitFor()
                } catch(t: Throwable) {}
            }
            return@setOnPreferenceClickListener true
        }
    }
}