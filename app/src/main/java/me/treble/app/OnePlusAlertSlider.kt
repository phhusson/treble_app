package me.treble.app

import android.content.Context
import android.media.AudioManager
import android.os.UEventObserver
import me.treble.app.Tools.audioManager

object OnePlusAlertSlider: EntryStartup {
    override fun startup(ctxt: Context) {
        if(!Tools.vendorFp.contains("OnePlus6")) return
        object : UEventObserver() {
            override fun onUEvent(event: UEventObserver.UEvent) {
                try {
                    android.util.Log.v("PHH", "USB UEVENT: " + event.toString())
                    val state = event.get("STATE")

                    val ringing = state.contains("USB=0")
                    val silent = state.contains("(null)=0")
                    val vibrate = state.contains("USB_HOST=0")
                    android.util.Log.v("PHH", "Got ringing = $ringing, silent = $silent, vibrate = $vibrate")
                    if (ringing && !silent && !vibrate)
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL)
                    if (silent && !ringing && !vibrate)
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT)
                    if (vibrate && !silent && !ringing)
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE)
                } catch (e: Exception) {
                    android.util.Log.d("PHH", "Failed parsing uevent", e)
                }

            }
        }.startObserving("DEVPATH=/devices/platform/soc/soc:tri_state_key")
    }
}