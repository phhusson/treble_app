package me.phh.treble.app

import android.content.pm.PackageInfo
import android.os.Bundle
import androidx.preference.ListPreference

object StyleSettings : Settings {
    val accentColor = "key_style_accent_color"
    val iconShape = "key_style_icon_shape"
    val fontFamily = "key_style_font_family"

    override fun enabled() = true
}

class StyleSettingsFragment : SettingsFragment() {
    override val preferencesResId = R.xml.pref_style
    private var packages = listOf<PackageInfo>();

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        super.onCreatePreferences(savedInstanceState, rootKey)

        packages = activity.packageManager.getInstalledPackages(0)

        val accentPref = findPreference<ListPreference>(StyleSettings.accentColor)!!
        val colorOverlays = OverlayPicker.getThemeOverlays(OverlayPicker.ThemeOverlay.AccentColor)
        val accentEntries = listOf("Default") + colorOverlays.map { getTargetName(it.packageName) }
        val accentValues = listOf("") + colorOverlays.map { it.packageName }

        accentPref.setEntries(accentEntries.toTypedArray())
        accentPref.setEntryValues(accentValues.toTypedArray())

        val shapePref = findPreference<ListPreference>(StyleSettings.iconShape)!!
        val iconOverlays = OverlayPicker.getThemeOverlays(OverlayPicker.ThemeOverlay.IconShape)
        val shapeEntries = listOf("Default") + iconOverlays.map { getTargetName(it.packageName) }
        val shapeValues = listOf("") + iconOverlays.map { it.packageName }

        shapePref.setEntries(shapeEntries.toTypedArray())
        shapePref.setEntryValues(shapeValues.toTypedArray())

        val fontPref = findPreference<ListPreference>(StyleSettings.fontFamily)!!
        val fontOverlays = OverlayPicker.getThemeOverlays(OverlayPicker.ThemeOverlay.FontFamily)
        val fontEntries = listOf("Default") + fontOverlays.map { getTargetName(it.packageName) }
        val fontValues = listOf("") + fontOverlays.map { it.packageName }

        fontPref.setEntries(fontEntries.toTypedArray())
        fontPref.setEntryValues(fontValues.toTypedArray())
    }

    fun getTargetName(p: String): String {
        var targetName = p.substringAfterLast(".").capitalize()
        val packageInfo = packages.find { it.packageName == p }
        if (packageInfo != null) {
            targetName = packageInfo.applicationInfo.loadLabel(activity.packageManager).toString()
        }
        return targetName
    }
}
