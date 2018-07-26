package me.treble.app

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log

class EntryService: Service() {
    private fun tryC(fnc: () -> Unit) {
        try {
            fnc()
        } catch(e: Exception) {
            Log.d("PHH", "Caught", e)
        }
    }
    override fun onBind(intent: Intent): IBinder? {
        tryC { Tools.startup(this) }
        tryC { QtiAudio.startup(this) }
        tryC { OnePlusAlertSlider.startup(this) }
        return null
    }
}

class Starter: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action != Intent.ACTION_BOOT_COMPLETED) return
        context.startService(Intent(context, EntryService::class.java))
    }
}

interface EntryStartup {
    fun startup(ctxt: Context)
}