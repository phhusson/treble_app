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

    override fun startup(ctxt: Context) {
        thread {
            for(slot in listOf("slot1", "slot2")) {
                try {
                    val svc = vendor.qti.hardware.radio.am.V1_0.IQcRilAudio.getService(slot)
                    svc.setCallback(cbA)
                } catch (e: Exception) {
                    Log.d("PHH", "Failed setting vendor.qti.hardware.radio.am $slot cb $e")
                }

                try {
                    val svc = vendor.qti.qcril.am.V1_0.IQcRilAudio.getService(slot)
                    svc.setCallback(cbB)
                } catch (e: Exception) {
                    Log.d("PHH", "Failed setting vendor.qti.hardware.radio.am $slot cb $e")
                }
            }

            Log.d("PHH", "Checking CAF IMS status")
            val installed = ctxt.packageManager.getInstalledPackages(0).find { it.packageName == "org.codeaurora.ims" } != null
            val imsRroProperty = "persist.sys.phh.ims.caf"
            Log.d("PHH", "CAF IMS $installed installed")
            Thread.sleep(30*1000)
            if(installed) {
                SystemProperties.set(imsRroProperty, "true")
                val replaceIntent =
                        Intent(Intent.ACTION_PACKAGE_CHANGED)
                                .setData(Uri.parse("package:org.codeaurora.ims"))
                                .putExtra(Intent.EXTRA_UID, 0)
                                .putExtra(Intent.EXTRA_DONT_KILL_APP, false)
                                .putExtra(Intent.EXTRA_CHANGED_COMPONENT_NAME_LIST, emptyArray<String>())
                ctxt.sendBroadcastAsUser(replaceIntent, UserHandle.SYSTEM)
            } else {
                SystemProperties.set(imsRroProperty, "false")
            }
        }
    }
    companion object: EntryStartup {
        override fun startup(ctxt: Context) {
            QtiAudio().startup(ctxt)
        }
    }
}