package me.phh.treble.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.media.AudioSystem
import android.util.Log

class HuaweiAudio : EntryStartup, BroadcastReceiver() {
    private var audioManager: AudioManager? = null
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_HEADSET_PLUG)
            return
        val state = intent.getIntExtra("state", -1)
        when(state) {
            AudioSystem.DEVICE_STATE_UNAVAILABLE -> HuaweiHeadsetUtils.speaker(audioManager!!)
            AudioSystem.DEVICE_STATE_AVAILABLE -> HuaweiHeadsetUtils.headset(audioManager!!)
            else ->
                Log.e("PlugReceiver", "Unrecognised headset plug state!", Throwable())
        }
        // Apply the changes by setting the volume to the current volume
        // Fails in DND but to exit that you must change the volume later so meh.
        val volume = audioManager!!.getStreamVolume(AudioManager.STREAM_MUSIC)
        audioManager!!.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0)
    }

    override fun startup(ctxt: Context) {
        audioManager = ctxt.getSystemService(AudioManager::class.java)
        val filter = IntentFilter(Intent.ACTION_HEADSET_PLUG)
        val plugReceiver = this
        ctxt.registerReceiver(plugReceiver, filter)
        Log.d(tag, "Registered for headset plug")
    }

    fun shutdown(ctxt: Context) {
        ctxt.unregisterReceiver(this)
        Log.d(tag, "Unregistered for headset plug")
    }

    companion object : EntryStartup {
        const val tag = "HuaweiAudio"
        private var self: HuaweiAudio? = null
        override fun startup(ctxt: Context) {
            self = HuaweiAudio()
            self!!.startup(ctxt)
        }
        fun shutdown(ctxt: Context) {
            self?.shutdown(ctxt)
            // Return to normal state if we were initialized earlier
            self?.audioManager?.let { HuaweiHeadsetUtils.speaker(it) }
        }
    }
}