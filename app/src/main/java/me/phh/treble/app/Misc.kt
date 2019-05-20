package me.phh.treble.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.SystemProperties
import android.preference.PreferenceManager
import android.provider.Settings
import android.util.Log
import java.lang.ref.WeakReference

@SuppressLint("StaticFieldLeak")
object Misc: EntryStartup {
    fun safeSetprop(key: String, value: String?) {
        try {
            SystemProperties.set(key, value)
        } catch (e: Exception) {
            Log.d("PHH", "Failed setting prop $key", e)
        }
    }
    lateinit var ctxt: WeakReference<Context>
    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        val c = ctxt.get()
        if(c == null) return@OnSharedPreferenceChangeListener
        when(key) {
            MiscSettings.mobileSignal -> {
                val value = sp.getString(key, "default")
                SystemProperties.set("persist.sys.signal.level", value)
                Log.d("PHH", "Setting signal level method to $value")
            }
            MiscSettings.fpsDivisor -> {
                val value = sp.getString(key, "1")
                Log.d("PHH", "Setting fps divisor to $value")
                Settings.Global.putString(c.contentResolver, "fps_divisor", value)
            }
            MiscSettings.maxAspectRatioPreO -> {
                val value = sp.getString(key, "1.86")
                SystemProperties.set("persist.sys.max_aspect_ratio.pre_o", value)
                Log.d("PHH", "Setting max aspect ratio for pre-o app $value")
            }
            MiscSettings.multiCameras -> {
                val value = sp.getBoolean(key, false)

                if(value ||
                    SystemProperties.get("vendor.camera.aux.packagelist", null) == null ||
                    SystemProperties.get("camera.aux.packagelist", null) == null ) {
                    safeSetprop("vendor.camera.aux.packagelist", if (value) "nothing" else null)
                    safeSetprop("camera.aux.packagelist", if(value) "nothing" else null)
                    safeSetprop("ctl.restart", "vendor.camera-provider-2-4")
                    safeSetprop("ctl.restart", "camera-provider-2-4")
                }
            }
            MiscSettings.headsetFix -> {
                val value = sp.getBoolean(key, HuaweiSettings.enabled())
                if (! sp.contains(key))
                Log.d("PHH", "Setting Huawei headset fix to $value")
                if (value) {
                    Log.d("PHH", "starting huaweiaudio")
                    ForceHeadsetAudio.startup(c)
                } else {
                    Log.d("PHH", "stopping huaweiaudio")
                    ForceHeadsetAudio.shutdown(c)
                }
            }
            MiscSettings.roundedCorners -> {
                val value = sp.getString(key, "-1").toInt()
                if(value >= 0) {
                    //Settings.Secure.putInt(c.contentResolver,"sysui_rounded_size", value)
                    Settings.Secure.putInt(c.contentResolver,"sysui_rounded_content_padding", value)
                }
            }
        }
    }

    override fun startup(ctxt: Context) {

        if (!MiscSettings.enabled()) return

        val sp = PreferenceManager.getDefaultSharedPreferences(ctxt)
        sp.registerOnSharedPreferenceChangeListener(spListener)

        this.ctxt = WeakReference(ctxt.applicationContext)

        //Refresh parameters on boot
        spListener.onSharedPreferenceChanged(sp, MiscSettings.fpsDivisor)
        spListener.onSharedPreferenceChanged(sp, MiscSettings.mobileSignal)
        spListener.onSharedPreferenceChanged(sp, MiscSettings.maxAspectRatioPreO)
        spListener.onSharedPreferenceChanged(sp, MiscSettings.multiCameras)
        if (! sp.contains(MiscSettings.headsetFix))
            sp.edit().putBoolean(MiscSettings.headsetFix, HuaweiSettings.enabled()).commit()
        spListener.onSharedPreferenceChanged(sp, MiscSettings.headsetFix)
    }
}
