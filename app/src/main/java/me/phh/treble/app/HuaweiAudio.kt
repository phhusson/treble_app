package me.phh.treble.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.util.Log

class HuaweiAudio : EntryStartup, BroadcastReceiver() {
    private var audioManager: AudioManager? = null
    override fun onReceive(context: Context, intent: Intent) {
        if (audioManager == null) {
            audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        }
        val state = intent.getIntExtra("state", -1)
        when(state) {
            0 -> HuaweiHeadsetUtils.speaker(audioManager!!)
            1 -> HuaweiHeadsetUtils.headset(audioManager!!)
            else ->
                Log.e("PlugReceiver", "Unrecognised headset plug state!", Throwable())
        }
        // Apply the changes by setting the volume to the current volume
        // Fails in DND but to exit that you must change the volume later so meh.
        audioManager!!.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager!!.getStreamVolume(AudioManager.STREAM_MUSIC), 0)
    }

    override fun startup(ctxt: Context) {
        val filter = IntentFilter(Intent.ACTION_HEADSET_PLUG)
        val plugReceiver = this
        ctxt.registerReceiver(plugReceiver, filter)
        Log.d(tag, "Registered for headset plug")
    }

    companion object {
        const val tag = "HuaweiAudio"
    }
}