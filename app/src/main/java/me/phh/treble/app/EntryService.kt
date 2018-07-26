package me.phh.treble.app

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.UserHandle
import android.util.Log

class EntryService: Service() {
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun tryC(fnc: () -> Unit) {
        try {
            fnc()
        } catch(e: Exception) {
            Log.d("PHH", "Caught", e)
        }
    }

    override fun onCreate() {
        Log.d("PHH", "Starting 1")
        tryC { Tools.startup(this) }
        Log.d("PHH", "Starting 2")
        tryC { QtiAudio.startup(this) }
        Log.d("PHH", "Starting 3")
        tryC { OnePlusAlertSlider.startup(this) }
        Log.d("PHH", "Starting 4")
        tryC { OverlayPicker.startup(this) }
        Log.d("PHH", "Starting 5")
    }
}

class Starter: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("PHH", "Starting service")
        //TODO: Check current user == "admin" == 0
        if(intent.action == Intent.ACTION_BOOT_COMPLETED) {
            context.startServiceAsUser(Intent(context, EntryService::class.java), UserHandle.SYSTEM)
        } else if (intent.action == "me.phh.update"){
            UpdateApplier.applyUpdate(intent.data.path)
        }
    }
}

interface EntryStartup {
    fun startup(ctxt: Context)
}