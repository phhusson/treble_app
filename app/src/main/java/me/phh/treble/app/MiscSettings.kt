package me.phh.treble.app

import android.app.AlertDialog
import android.app.Application
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.os.SystemProperties
import android.util.Log
import androidx.preference.ListPreference
import androidx.preference.Preference

object MiscSettings : Settings {
    val mobileSignal = "key_misc_mobile_signal"
    val fpsDivisor = "key_misc_fps_divisor"
    val displayFps = "key_misc_display_fps"
    val maxAspectRatioPreO = "key_misc_max_aspect_ratio_pre_o"
    val multiCameras = "key_misc_multi_camera"
    val forceCamera2APIHAL3 = "key_misc_force_camera2api_hal3"
    val headsetFix = "key_huawei_headset_fix"
    val roundedCorners = "key_misc_rounded_corners"
    val roundedCornersOverlay = "key_misc_rounded_corners_overlay"
    val linearBrightness = "key_misc_linear_brightness"
    val disableButtonsBacklight = "key_misc_disable_buttons_backlight"
    val forceNavbarOff = "key_misc_force_navbar_off"
    val bluetooth = "key_misc_bluetooth"
    val securize = "key_misc_securize"
    val removeTelephony = "key_misc_removetelephony"
    val remotectl = "key_misc_remotectl"
    val disableAudioEffects = "key_misc_disable_audio_effects"
    val cameraTimestampOverride = "key_misc_camera_timestamp"
    val forceA2dpOffloadDisable = "key_misc_force_a2dp_offload_disable"
    val noHwcomposer = "key_misc_no_hwcomposer"
    val storageFUSE = "key_misc_storage_fuse"
    val backlightScale = "key_misc_backlight_scale"
    val headsetDevinput = "key_misc_headset_devinput"
    val restartRil = "key_misc_restart_ril"
    val minimalBrightness = "key_misc_minimal_brightness"
    val aod = "key_misc_aod"
    val dt2w = "key_misc_dt2w"
    val restartSystemUI = "key_misc_restart_systemui"
    val dumpLogs = "key_misc_dump_logs"

    override fun enabled() = true
}

class MiscSettingsFragment : SettingsFragment() {
    override val preferencesResId = R.xml.pref_misc
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        super.onCreatePreferences(savedInstanceState, rootKey)

        val securizePref = findPreference<Preference>(MiscSettings.securize)
        securizePref!!.setOnPreferenceClickListener {
                val builder = AlertDialog.Builder( this.getActivity() )
                builder.setTitle(getString(R.string.remove_root))
                builder.setMessage(getString(R.string.continue_question))

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                var cmds = listOf(
                        "/sbin/su -c /system/bin/phh-securize.sh",
                        "/system/xbin/su -c /system/bin/phh-securize.sh",
                        "/system/xbin/phh-su -c /system/bin/phh-securize.sh",
                        "/sbin/su 0 /system/bin/phh-securize.sh",
                        "/system/xbin/su 0 /system/bin/phh-securize.sh",
                        "/system/xbin/phh-su 0 /system/bin/phh-securize.sh"
                )
                for(cmd in cmds) {
                    try {
                        Runtime.getRuntime().exec(cmd).waitFor()
                    } catch(t: Throwable) {}
                }
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
            }

            builder.show()
            return@setOnPreferenceClickListener true
        }

        val removeTelephonyPref = findPreference<Preference>(MiscSettings.removeTelephony)
        removeTelephonyPref!!.setOnPreferenceClickListener {

            val builder = AlertDialog.Builder( this.getActivity() )
            builder.setTitle(getString(R.string.remove_telephony_subsystem))
            builder.setMessage(getString(R.string.continue_question))

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                var cmds = listOf(
                        "/sbin/su -c /system/bin/remove-telephony.sh",
                        "/system/xbin/su -c /system/bin/remove-telephony.sh",
                        "/system/xbin/phh-su -c /system/bin/remove-telephony.sh",
                        "/sbin/su 0 /system/bin/remove-telephony.sh",
                        "/system/xbin/su 0 /system/bin/remove-telephony.sh",
                        "/system/xbin/phh-su 0 /system/bin/remove-telephony.sh"
                )
                for(cmd in cmds) {
                    try {
                        Runtime.getRuntime().exec(cmd).waitFor()
                    } catch(t: Throwable) {}
                }
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
            }

            builder.show()
            return@setOnPreferenceClickListener true
        }

        val fpsPref = findPreference<ListPreference>(MiscSettings.displayFps)!!
        val displayManager = activity.getSystemService(DisplayManager::class.java)
        for(display in displayManager.displays) {
            Log.d("PHH", "Got display $display")
            for(mode in display.supportedModes) {
                Log.d("PHH", "\tMode ${mode.modeId} $mode")
            }
        }

        val fpsEntries = listOf("Don't force") + displayManager.displays[0].supportedModes.map {
            val fps = it.refreshRate
            val w = it.physicalWidth
            val h = it.physicalHeight
            "${w}x${h}@${fps}"
        }
        val fpsValues = listOf("-1") + displayManager.displays[0].supportedModes.map { (it.modeId - 1).toString() }

        fpsPref.setEntries(fpsEntries.toTypedArray())
        fpsPref.setEntryValues(fpsValues.toTypedArray())

        val restartSystemUIPref = findPreference<Preference>(MiscSettings.restartSystemUI)
        restartSystemUIPref!!.setOnPreferenceClickListener {
            SystemProperties.set("sys.phh.restart_sysui", "true")
            return@setOnPreferenceClickListener true
        }

        val dumpLogsPref = findPreference<Preference>(MiscSettings.dumpLogs)
        dumpLogsPref!!.setOnPreferenceClickListener {
            SystemProperties.set("sys.phh.dump_logs", "true")
            return@setOnPreferenceClickListener true
        }
    }
}
