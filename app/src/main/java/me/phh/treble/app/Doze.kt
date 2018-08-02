package me.phh.treble.app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.UserHandle
import android.preference.PreferenceManager
import android.util.Log
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

object Doze: EntryStartup {
    val dozeHandlerThread = HandlerThread("Doze Handler").also { it.start() }
    val dozeHandler = Handler(dozeHandlerThread.looper)

    class AccelerometerListener {
        val queue = LinkedBlockingQueue<FloatArray>()
        val cb = object: SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }

            override fun onSensorChanged(event: SensorEvent) {
                queue.add(event.values)
                sensorManager.unregisterListener(this)
            }
        }

        fun read(): FloatArray {
            if(Looper.getMainLooper() == Looper.myLooper())
                throw Exception("This is a blocking function. Don't call me from main Looper.")
            sensorManager.registerListener(cb, accelerometerSensor, 1000)
            val result = queue.poll(1, TimeUnit.SECONDS)
            return result?: FloatArray(3, {0.0f})
        }

        fun isFaceUp(): Boolean {
            val v = read()
            val isUp = v[2] > 5.0f
            Log.d("PHH", "Got ${v[2]} $isUp")
            return isUp
        }
    }

    class Pocket {
        val NEAR = true
        val FAR = false

        //If events are closer than this, then guess this is a hand gesture
        val HANDWAVE_MAX_NS = 1000*1000*1000L // 1s
        //If events are more separated than this, then guess this is a pocket gesture
        val POCKET_MIN_NS = 60*1000*1000*1000L // 60s

        var state = FAR
        var lastEvent = -1L

        val maxRange = proximitySensor.maximumRange
        val threshold = if(maxRange >= 5.0f) 5.0f else maxRange

        fun update(event: SensorEvent) {
            Log.d("PHH", "Pocket got updated proximity to ${event.values[0]}")
            if(event.sensor != proximitySensor) return
            val newState = if(event.values[0] >= threshold) FAR else NEAR
            if(newState == state) return
            state = newState
            if(lastEvent == -1L) {
                lastEvent = event.timestamp
                return
            }

            //If we're far for the first time in a long time
            //Consider it a "pocket" event
            if(event.timestamp > (lastEvent + POCKET_MIN_NS)) {
                if(pocketEnabled && state == FAR) {
                    Log.d("PHH", "Got pocket event")
                    pulseDoze()
                }
            }

            //If we're far but we've been close not long enough
            //Consider it an "handwave" event
            if(event.timestamp < (lastEvent + HANDWAVE_MAX_NS)) {
                if(handwaveEnabled && state == FAR) {
                    Log.d("PHH", "Got handwave event")
                    dozeHandler.post {
                        if (accelerometer?.isFaceUp() == true)
                            pulseDoze()
                    }
                }
            }
            lastEvent = event.timestamp
        }
    }

    var enabled: Boolean = false
    var handwaveEnabled = false
    var pocketEnabled = false
    lateinit var sensorManager: SensorManager
    lateinit var proximitySensor: Sensor
    lateinit var accelerometerSensor: Sensor
    var pocket: Pocket? = null
    var accelerometer: AccelerometerListener? = null

    fun updateState(handwave: Boolean, pocket: Boolean) {
        handwaveEnabled = handwave
        pocketEnabled = pocket

        val newState = if(handwave || pocket) true else false
        if(newState && !enabled) {
            Log.d("PHH", "Starting Doze service")
            this.pocket = Pocket()
            registerListeners()
        }
        if(enabled && !newState) {
            unregisterListeners()
            this.pocket = null
        }
        enabled = newState
    }

    val sensorListener = object: SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            pocket?.update(event)
        }
    }

    fun registerListeners() {
        //Request for once every 1000s, we only want updates anyway
        sensorManager.registerListener(sensorListener, proximitySensor, 1000*1000*1000)
    }

    fun unregisterListeners() {
        sensorManager.unregisterListener(sensorListener)
    }

    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        when(key) {
            DozeSettings.handwaveKey, DozeSettings.pocketKey -> {
                updateState(
                        sp.getBoolean(DozeSettings.handwaveKey, false),
                        sp.getBoolean(DozeSettings.pocketKey, false))
            }
        }
    }

    override fun startup(ctxt: Context) {
        Log.d("PHH", "Starting Doze service")
        sensorManager = ctxt.getSystemService(SensorManager::class.java)
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY, true)
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER, false)

        val sp = PreferenceManager.getDefaultSharedPreferences(ctxt)
        sp.registerOnSharedPreferenceChangeListener(spListener)

        //Refresh parameters on boot
        spListener.onSharedPreferenceChanged(sp, DozeSettings.handwaveKey)
        spListener.onSharedPreferenceChanged(sp, DozeSettings.pocketKey)
        accelerometer = AccelerometerListener()
    }

    fun pulseDoze() {
        EntryService.service?.sendBroadcastAsUser(Intent("com.android.systemui.doze.pulse"), UserHandle.ALL)
    }
}