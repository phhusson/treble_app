package me.phh.treble.app

import android.content.Context
import android.media.AudioSystem
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
        }
    }
    companion object: EntryStartup {
        override fun startup(ctxt: Context) {
            QtiAudio().startup(ctxt)
        }
    }
}