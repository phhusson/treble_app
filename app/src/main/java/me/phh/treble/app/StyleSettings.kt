package me.phh.treble.app

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
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        super.onCreatePreferences(savedInstanceState, rootKey)

        val accentPref = findPreference<ListPreference>(StyleSettings.accentColor)!!
        val accentList = OverlayPicker.getOverlays("android").filter { it.packageName.startsWith("com.android.theme.color.") }
        val accentEntries = listOf("Default") + accentList.map { getTargetName(it.packageName) }
        val accentValues = listOf("") + accentList.map { it.packageName }

        accentPref.setEntries(accentEntries.toTypedArray())
        accentPref.setEntryValues(accentValues.toTypedArray())

        val shapePref = findPreference<ListPreference>(StyleSettings.iconShape)!!
        val shapeList = OverlayPicker.getOverlays("android").filter { it.packageName.startsWith("com.android.theme.icon.") }
        val shapeEntries = listOf("Default") + shapeList.map { getTargetName(it.packageName) }
        val shapeValues = listOf("") + shapeList.map { it.packageName }

        shapePref.setEntries(shapeEntries.toTypedArray())
        shapePref.setEntryValues(shapeValues.toTypedArray())

        val fontPref = findPreference<ListPreference>(StyleSettings.fontFamily)!!
        val fontList = OverlayPicker.getOverlays("android").filter { it.packageName.startsWith("com.android.theme.font.") }
        val fontEntries = listOf("Default") + fontList.map { getTargetName(it.packageName) }
        val fontValues = listOf("") + fontList.map { it.packageName }

        fontPref.setEntries(fontEntries.toTypedArray())
        fontPref.setEntryValues(fontValues.toTypedArray())
    }

    fun getTargetName(p: String): String {
        var targetName = p.substringAfterLast(".").capitalize()
        val packageInfo = activity.packageManager.getInstalledPackages(0).find { it.packageName == p }
        if (packageInfo != null) {
            targetName = packageInfo.applicationInfo.loadLabel(activity.packageManager).toString()
        }
        return targetName
    }
}
