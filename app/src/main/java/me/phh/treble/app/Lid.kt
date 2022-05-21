package me.phh.treble.app

import android.content.Context
import android.hardware.*
import android.os.PowerManager
import android.os.SystemClock
import android.util.Log

object Lid: EntryStartup {
    override fun startup(ctxt: Context) {
        if(Tools.vendorFpLow.startsWith("Lenovo/TB-9707F_PRC/TB-9707F".lowercase())) {
            val sensorManager = ctxt.getSystemService(SensorManager::class.java)
            val lidSensor = sensorManager.getSensorList(Sensor.TYPE_ALL).firstOrNull() { it.name.contains("ah1902 Hall Effect Sensor Wakeup")}
            if(lidSensor == null) {
                Log.d("PHH", "Failed finding sensor for lid wakeup")
                for(s in sensorManager.getSensorList(Sensor.TYPE_ALL)) {
                    Log.d("PHH", " - '${s.name}'")
                }
            }
            Log.d("PHH", "Found lid sensor $lidSensor")
            val powerManager = ctxt.getSystemService(PowerManager::class.java)
            sensorManager.registerListener(object: SensorEventListener {
                override fun onSensorChanged(p0: SensorEvent) {
                    Log.d("PHH", "Received LID event $p0")
                    Log.d("PHH", "Lid value is ${p0.values[0]}, ${p0.values[0] == 0.0f}, ${p0.values[0] == 1.0f}")

                    if(p0.values[0] == 1.0f) { // Lid is opening, wakeup
                        Log.d("PHH", "Lid Waking up")
                        PowerManager::class.java.getMethod("wakeUp", Long::class.java).invoke(powerManager, SystemClock.uptimeMillis())
                    } else if(p0.values[0] == 0.0f) { // Lid is closing, sleeping
                        Log.d("PHH", "Lid Sleeeping")
                        PowerManager::class.java.getMethod("goToSleep", Long::class.java).invoke(powerManager, SystemClock.uptimeMillis())
                    }
                }

                override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                }
            }, lidSensor, 1000*1000)
        }
    }
}