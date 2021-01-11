package me.phh.treble.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.lang.ref.WeakReference

@SuppressLint("StaticFieldLeak")
object Style: EntryStartup {
    lateinit var ctxt: WeakReference<Context>
    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        val c = ctxt.get()
        if(c == null) return@OnSharedPreferenceChangeListener
        when(key) {
            StyleSettings.accentColor -> {
                val value = sp.getString(key, "")
                val allOverlays = OverlayPicker.getOverlays("android")
                        .filter { it.packageName.startsWith("com.android.theme.color.") }
                allOverlays
                        .filter { it.packageName != value }
                        .forEach { OverlayPicker.setOverlayEnabled(it.packageName, false) }
                if (!value.isNullOrEmpty()) {
                    OverlayPicker.setOverlayEnabled(value, true)
                }
            }
            StyleSettings.iconShape -> {
                val value = sp.getString(key, "")
                val allOverlays = OverlayPicker.getOverlays("android")
                        .filter { it.packageName.startsWith("com.android.theme.icon.") }
                allOverlays
                        .filter { it.packageName != value }
                        .forEach { OverlayPicker.setOverlayEnabled(it.packageName, false) }
                if (!value.isNullOrEmpty()) {
                    OverlayPicker.setOverlayEnabled(value, true)
                }
            }
            StyleSettings.fontFamily -> {
                val value = sp.getString(key, "")
                val allOverlays = OverlayPicker.getOverlays("android")
                        .filter { it.packageName.startsWith("com.android.theme.font.") }
                allOverlays
                        .filter { it.packageName != value }
                        .forEach { OverlayPicker.setOverlayEnabled(it.packageName, false) }
                if (!value.isNullOrEmpty()) {
                    OverlayPicker.setOverlayEnabled(value, true)
                }
            }
            StyleSettings.iconPack -> {
                val value = sp.getString(key, "")
                val genericValue = value.toString().substringBeforeLast(".")
                val androidOverlays = OverlayPicker.getOverlays("android")
                        .filter { it.packageName.startsWith("com.android.theme.icon_pack.") }
                val settingsOverlays = OverlayPicker.getOverlays("com.android.settings")
                        .filter { it.packageName.startsWith("com.android.theme.icon_pack.") }
                val uiOverlays = OverlayPicker.getOverlays("com.android.systemui")
                        .filter { it.packageName.startsWith("com.android.theme.icon_pack.") }
                val allOverlays = androidOverlays + settingsOverlays + uiOverlays
                allOverlays
                        .filter {
                            it.packageName != genericValue + ".android" &&
                            it.packageName != genericValue + ".settings" &&
                            it.packageName != genericValue + ".systemui"
                        }
                        .forEach { OverlayPicker.setOverlayEnabled(it.packageName, false) }
                if (!value.isNullOrEmpty()) {
                    OverlayPicker.setOverlayEnabled(genericValue + ".android", true)
                    OverlayPicker.setOverlayEnabled(genericValue + ".settings", true)
                    OverlayPicker.setOverlayEnabled(genericValue + ".systemui", true)
                }
            }
        }
    }

    override fun startup(ctxt: Context) {
        if (!StyleSettings.enabled()) return

        val sp = PreferenceManager.getDefaultSharedPreferences(ctxt)
        sp.registerOnSharedPreferenceChangeListener(spListener)

        this.ctxt = WeakReference(ctxt.applicationContext)

        //Refresh parameters on boot
        spListener.onSharedPreferenceChanged(sp, StyleSettings.accentColor)
        spListener.onSharedPreferenceChanged(sp, StyleSettings.iconShape)
        spListener.onSharedPreferenceChanged(sp, StyleSettings.fontFamily)
        spListener.onSharedPreferenceChanged(sp, StyleSettings.iconPack)
    }
}
