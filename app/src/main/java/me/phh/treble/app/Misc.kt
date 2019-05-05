package me.phh.treble.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.SystemProperties
import android.preference.PreferenceManager
import android.provider.Settings
import android.util.Log

@SuppressLint("StaticFieldLeak")
object Misc: EntryStartup {
    fun safeSetprop(key: String, value: String?) {
        try {
            SystemProperties.set(key, value)
        } catch (e: Exception) {
            Log.d("PHH", "Failed setting prop $key", e)
        }
    }
    lateinit var ctxt: Context
    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        when(key) {
            MiscSettings.mobileSignal -> {
                val value = sp.getString(key, "default")
                SystemProperties.set("persist.sys.signal.level", value)
                Log.d("PHH", "Setting signal level method to $value")
            }
            MiscSettings.fpsDivisor -> {
                val value = sp.getString(key, "1")
                Log.d("PHH", "Setting fps divisor to $value")
                Settings.Global.putString(ctxt.contentResolver, "fps_divisor", value)
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
                    ForceHeadsetAudio.startup(ctxt)
                } else {
                    Log.d("PHH", "stopping huaweiaudio")
                    ForceHeadsetAudio.shutdown(ctxt)
                }
            }
        }
    }

    override fun startup(ctxt: Context) {

        if (!MiscSettings.enabled()) return

        val sp = PreferenceManager.getDefaultSharedPreferences(ctxt)
        sp.registerOnSharedPreferenceChangeListener(spListener)

        this.ctxt = ctxt.applicationContext

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
