package me.phh.treble.app


import android.media.AudioManager
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

object HuaweiHeadsetUtils {
    private val lock = ReentrantLock(true)
    private var mode: Int = AudioManager.MODE_INVALID
    fun speaker(audioManager: AudioManager) {
        lock.withLock {
            mode = audioManager.mode
            audioManager.mode = AudioManager.MODE_IN_COMMUNICATION
            audioManager.isSpeakerphoneOn = true
            // Again. Who knows why, but otherwise it just stays in in-call speakerphone
            audioManager.mode = AudioManager.MODE_IN_COMMUNICATION
            audioManager.mode = AudioManager.MODE_NORMAL
            audioManager.mode = mode
        }
    }

    fun headset(audioManager: AudioManager) {
        lock.withLock {
            mode = audioManager.mode
            audioManager.isSpeakerphoneOn = false
            audioManager.mode = AudioManager.MODE_IN_COMMUNICATION
            audioManager.isSpeakerphoneOn = false
            audioManager.mode = AudioManager.MODE_NORMAL
            audioManager.isSpeakerphoneOn = false
            audioManager.mode = mode
        }
    }
}
