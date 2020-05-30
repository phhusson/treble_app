package me.phh.treble.app

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioSystem
import android.net.Uri
import android.os.SystemProperties
import android.os.UserHandle
import android.util.Log
import dalvik.system.PathClassLoader
import kotlin.concurrent.thread

class QtiAudio: EntryStartup {
    val cbA = object: vendor.qti.hardware.radio.am.V1_0.IQcRilAudioCallback.Stub() {
        override fun getParameters(str: String): String {
            return AudioSystem.getParameters(str)
        }

        override fun setParameters(str: String): Int {
            return AudioSystem.setParameters(str)
        }
    }
    val cbB = object: vendor.qti.qcril.am.V1_0.IQcRilAudioCallback.Stub() {
        override fun getParameters(str: String?): String {
            return AudioSystem.getParameters(str)
        }

        override fun setParameters(str: String?): Int {
            return AudioSystem.setParameters(str)
        }
    }

    fun handleDynIms(ctxt: Context, pkgName: String, property: String) {
            Log.d("PHH", "Checking IMS status $pkgName $property")
            val installed = ctxt.packageManager.getInstalledPackages(0).find { it.packageName == pkgName } != null
            val imsRroProperty = property
            Log.d("PHH", "CAF IMS $installed installed")
            if(installed) {
                SystemProperties.set(imsRroProperty, "true")
                val replaceIntent =
                        Intent(Intent.ACTION_PACKAGE_CHANGED)
                                .setData(Uri.parse("package:$pkgName"))
                                .putExtra(Intent.EXTRA_UID, 0)
                                .putExtra(Intent.EXTRA_DONT_KILL_APP, false)
                                .putExtra(Intent.EXTRA_CHANGED_COMPONENT_NAME_LIST, emptyArray<String>())
                ctxt.sendBroadcastAsUser(replaceIntent, UserHandle.SYSTEM)
            } else {
                SystemProperties.set(imsRroProperty, "false")
            }
    }

    override fun startup(ctxt: Context) {
        thread {
            for(slot in listOf("slot1", "slot2", "default")) {
                try {
                    val svc = vendor.qti.hardware.radio.am.V1_0.IQcRilAudio.getService(slot)
                    svc.setCallback(cbA)
                    isQualcommDevice = true
                } catch (e: Exception) {
                    Log.d("PHH", "Failed setting vendor.qti.hardware.radio.am $slot cb $e")
                }

                try {
                    val svc = vendor.qti.qcril.am.V1_0.IQcRilAudio.getService(slot)
                    svc.setCallback(cbB)
                    isQualcommDevice = true
                } catch (e: Exception) {
                    Log.d("PHH", "Failed setting vendor.qti.hardware.radio.am $slot cb $e")
                }
            }

            Thread.sleep(30*1000);
            handleDynIms(ctxt, "org.codeaurora.ims", "persist.sys.phh.ims.caf")
            handleDynIms(ctxt, "com.shannon.imsservice", "persist.sys.phh.ims.sec")
        }
    }
    companion object: EntryStartup {
        var isQualcommDevice = false
        override fun startup(ctxt: Context) {
            QtiAudio().startup(ctxt)
        }
    }
}
