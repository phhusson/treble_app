package me.phh.treble.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.hardware.display.DisplayManager
import android.os.Parcel
import android.os.ServiceManager
import android.os.SystemProperties
import android.preference.PreferenceManager
import android.provider.Settings
import android.util.Log
import java.lang.ref.WeakReference

@SuppressLint("StaticFieldLeak")
object Misc: EntryStartup {
    fun safeSetprop(key: String, value: String?) {
        try {
            Log.d("PHH", "Setting property $key to $value")
            SystemProperties.set(key, value)
        } catch (e: Exception) {
            Log.d("PHH", "Failed setting prop $key", e)
        }
    }

    val surfaceFlinger = ServiceManager.getService("SurfaceFlinger")
    fun forceFps(v: Int) {
        val data = Parcel.obtain()
        data.writeInterfaceToken("android.ui.ISurfaceComposer")
        data.writeInt(v)
        surfaceFlinger.transact(1035, data, null, 0)
        data.recycle()
        Log.d("PHH", "Set surface flinger forced fps/mode to $v")
    }

    lateinit var ctxt: WeakReference<Context>
    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        val c = ctxt.get()
        if(c == null) return@OnSharedPreferenceChangeListener
        val displayManager = c.getSystemService(DisplayManager::class.java)
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
            MiscSettings.cameraTimestampOverride -> {
                val value = sp.getString(key, "-1")
                Log.d("PHH", "Setting cameraTimestampOverride to $value")
                SystemProperties.set("persist.sys.phh.camera.force_timestampsource", value)
            }
            MiscSettings.maxAspectRatioPreO -> {
                val value = sp.getString(key, "1.86")
                SystemProperties.set("persist.sys.max_aspect_ratio.pre_o", value)
                Log.d("PHH", "Setting max aspect ratio for pre-o app $value")
            }
            MiscSettings.multiCameras -> {
                val value = sp.getBoolean(key, false)

                if (value ||
                        SystemProperties.get("vendor.camera.aux.packagelist", null) == null ||
                        SystemProperties.get("camera.aux.packagelist", null) == null) {
                    safeSetprop("vendor.camera.aux.packagelist", if (value) "nothing" else null)
                    safeSetprop("camera.aux.packagelist", if (value) "nothing" else null)
                    safeSetprop("ctl.restart", "vendor.camera-provider-2-4")
                    safeSetprop("ctl.restart", "camera-provider-2-4")
                }
            }
            MiscSettings.forceCamera2APIHAL3 -> {
                val value = sp.getBoolean(key, false)
                val defValue = "0"
                val newValue = if (value) "1" else defValue

                if (value ||
                        SystemProperties.get("persist.vendor.camera.HAL3.enabled", defValue) != newValue ||
                        SystemProperties.get("persist.vendor.camera.eis.enable", defValue) != newValue) {
                    safeSetprop("persist.vendor.camera.HAL3.enabled", newValue)
                    safeSetprop("persist.vendor.camera.eis.enable", newValue)
                    Log.d("PHH", "forced Camera2API HAL3 to $value")
                    // Restart services
                    safeSetprop("ctl.restart", "vendor.camera-provider-2-4")
                    safeSetprop("ctl.restart", "camera-provider-2-4")
                }
            }
            MiscSettings.headsetFix -> {
                val value = sp.getBoolean(key, HuaweiSettings.enabled())
                if (!sp.contains(key))
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
                if (value >= 0) {
                    Settings.Secure.putInt(c.contentResolver, "sysui_rounded_content_padding", value)
                }
            }
            MiscSettings.roundedCornersOverlay -> {
                val value = sp.getString(key, "-1").toFloat()
                if (value >= 0) {
                    Settings.Secure.putFloat(c.contentResolver, "sysui_rounded_size", value)
                }
            }
            MiscSettings.linearBrightness -> {
                val value = sp.getBoolean(key, false)
                SystemProperties.set("persist.sys.phh.linear_brightness", if (value) "true" else "false")
            }
            MiscSettings.disableButtonsBacklight -> {
                val value = sp.getBoolean(key, false)
                SystemProperties.set("persist.sys.phh.disable_buttons_light", if (value) "true" else "false")
            }
            MiscSettings.forceNavbarOff -> {
                val value = sp.getBoolean(key, false)
                SystemProperties.set("persist.sys.phh.mainkeys", if (value) "1" else "0")
            }
            MiscSettings.bluetooth -> {
                val value = sp.getString(key, "none")
                android.util.Log.d("PHH", "Setting bluetooth workaround to $value")
                when (value) {
                    "none" -> {
                        SystemProperties.set("persist.sys.bt.unsupport.features", "00000000")
                        SystemProperties.set("persist.sys.bt.unsupport.states", "00000000")
                        SystemProperties.set("persist.sys.bt.unsupport.stdfeatures", "00000000")
                    }
                    "mediatek" -> {
                        SystemProperties.set("persist.sys.bt.unsupport.features", "00000000")
                        SystemProperties.set("persist.sys.bt.unsupport.states", "00000000")
                        SystemProperties.set("persist.sys.bt.unsupport.stdfeatures", "000001")
                    }
                    "huawei" -> {
                        SystemProperties.set("persist.sys.bt.unsupport.features", "00000001")
                        SystemProperties.set("persist.sys.bt.unsupport.states", "000000000000000000000011111")
                        SystemProperties.set("persist.sys.bt.unsupport.stdfeatures", "000001")
                    }
                }
            }
            MiscSettings.displayFps -> {
                val value = sp.getString(key, "-1").toInt()
                val maxValue = displayManager.displays[0].supportedModes.size
                if(value>= maxValue) {
                    Log.d("PHH", "Trying to set impossible mode " + value)
                } else {
                    Log.d("PHH", "Trying to set mode " + value)
                    forceFps(value)
                }
            }
            MiscSettings.remotectl -> {
                val value = sp.getBoolean(key, false)
                SystemProperties.set("persist.sys.phh.remote", if (value) "true" else "false")
            }
            MiscSettings.disableAudioEffects -> {
                val value = sp.getBoolean(key, false)
                SystemProperties.set("persist.sys.phh.disable_audio_effects", if (value) "1" else "0")
            }
            MiscSettings.forceA2dpOffloadDisable -> {
                val value = sp.getBoolean(key, false)
                SystemProperties.set("persist.sys.phh.disable_a2dp_offload", if (value) "1" else "0")
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
        spListener.onSharedPreferenceChanged(sp, MiscSettings.cameraTimestampOverride)
        spListener.onSharedPreferenceChanged(sp, MiscSettings.mobileSignal)
        spListener.onSharedPreferenceChanged(sp, MiscSettings.maxAspectRatioPreO)
        spListener.onSharedPreferenceChanged(sp, MiscSettings.multiCameras)
        spListener.onSharedPreferenceChanged(sp, MiscSettings.forceCamera2APIHAL3)
        if (! sp.contains(MiscSettings.headsetFix))
            sp.edit().putBoolean(MiscSettings.headsetFix, HuaweiSettings.enabled()).commit()
        spListener.onSharedPreferenceChanged(sp, MiscSettings.headsetFix)
        spListener.onSharedPreferenceChanged(sp, MiscSettings.bluetooth)
        spListener.onSharedPreferenceChanged(sp, MiscSettings.displayFps)
    }
}
