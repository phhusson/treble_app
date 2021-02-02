package me.phh.treble.app

import android.content.pm.PackageInfo
import android.os.Bundle
import androidx.preference.ListPreference

object StyleSettings : Settings {
    val accentColor = "key_style_accent_color"
    val iconShape = "key_style_icon_shape"
    val fontFamily = "key_style_font_family"
    val iconPack = "key_style_icon_pack"

    override fun enabled() = true
}

class StyleSettingsFragment : SettingsFragment() {
    override val preferencesResId = R.xml.pref_style
    private var packages = listOf<PackageInfo>();

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        super.onCreatePreferences(savedInstanceState, rootKey)

        packages = activity.packageManager.getInstalledPackages(0)

        val accentColorPref = findPreference<ListPreference>(StyleSettings.accentColor)!!
        val accentColorOverlays = OverlayPicker.getThemeOverlays(OverlayPicker.ThemeOverlay.AccentColor)
        val accentColorEntries = listOf("Default") + accentColorOverlays.map { getTargetName(it.packageName) }
        val accentColorValues = listOf("") + accentColorOverlays.map { it.packageName }

        accentColorPref.setEntries(accentColorEntries.toTypedArray())
        accentColorPref.setEntryValues(accentColorValues.toTypedArray())

        val iconShapePref = findPreference<ListPreference>(StyleSettings.iconShape)!!
        val iconShapeOverlays = OverlayPicker.getThemeOverlays(OverlayPicker.ThemeOverlay.IconShape)
        val iconShapeEntries = listOf("Default") + iconShapeOverlays.map { getTargetName(it.packageName) }
        val iconShapeValues = listOf("") + iconShapeOverlays.map { it.packageName }

        iconShapePref.setEntries(iconShapeEntries.toTypedArray())
        iconShapePref.setEntryValues(iconShapeValues.toTypedArray())

        val fontFamilyPref = findPreference<ListPreference>(StyleSettings.fontFamily)!!
        val fontFamilyOverlays = OverlayPicker.getThemeOverlays(OverlayPicker.ThemeOverlay.FontFamily)
        val fontFamilyEntries = listOf("Default") + fontFamilyOverlays.map { getTargetName(it.packageName) }
        val fontFamilyValues = listOf("") + fontFamilyOverlays.map { it.packageName }

        fontFamilyPref.setEntries(fontFamilyEntries.toTypedArray())
        fontFamilyPref.setEntryValues(fontFamilyValues.toTypedArray())

        val iconPackPref = findPreference<ListPreference>(StyleSettings.iconPack)!!
        val iconPackOverlays = OverlayPicker.getThemeOverlays(OverlayPicker.ThemeOverlay.IconPack)
        var iconPackMap = hashMapOf<String, String>()
        iconPackOverlays.forEach() { iconPackMap = addOverlayToMap(iconPackMap, it.packageName) }
        val iconPackEntries = listOf("Default") + iconPackMap.values
        val iconPackValues = listOf("") + iconPackMap.keys

        iconPackPref.setEntries(iconPackEntries.toTypedArray())
        iconPackPref.setEntryValues(iconPackValues.toTypedArray())
    }

    fun getTargetName(p: String): String {
        var targetName = p.substringAfterLast(".").capitalize()
        val packageInfo = packages.find { it.packageName == p }
        if (packageInfo != null) {
            targetName = packageInfo.applicationInfo.loadLabel(activity.packageManager).toString()
        }
        return targetName
    }

    fun addOverlayToMap(map: HashMap<String, String>, o: String): HashMap<String, String> {
        val genericValue = o.substringBeforeLast(".")
        val duplicates = map.filterKeys { it.substringBeforeLast(".") == genericValue }
        if (duplicates.entries.size == 0)
        {
            map.put(o, getTargetName(o))
        }
        return map
    }
}
