package me.phh.treble.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.hardware.display.DisplayManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Parcel
import android.os.ServiceManager
import android.os.SystemProperties
import android.preference.PreferenceManager
import android.provider.Settings
import android.util.Log
import java.lang.ref.WeakReference

@SuppressLint("StaticFieldLeak")
object Ims: EntryStartup {
    lateinit var ctxt: WeakReference<Context>

    val networkListener = object: ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            Log.i("PHH", "Network $network is available!")
        }
        override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
            Log.i("PHH", "Received info about network $network, got $networkCapabilities")
        }
    }

    var registeredNetwork = false
    val spListener = SharedPreferences.OnSharedPreferenceChangeListener { sp, key ->
        val c = ctxt.get() ?: return@OnSharedPreferenceChangeListener
        val cm = c.getSystemService(ConnectivityManager::class.java)

        when(key) {
            ImsSettings.requestNetwork -> {
                val value = sp.getBoolean(key, false)
                if(value) {
                    val nwRequest = NetworkRequest.Builder()
                            .addCapability(NetworkCapabilities.NET_CAPABILITY_IMS)
                            .build()
                    cm.requestNetwork(nwRequest, networkListener)
                    registeredNetwork = true
                } else {
                    if(registeredNetwork) {
                        cm.unregisterNetworkCallback(networkListener)
                        registeredNetwork = false
                    }
                }
            }
            ImsSettings.forceEnableSettings -> {
                val value = if(sp.getBoolean(key, false)) "1" else "0"
                Misc.safeSetprop("persist.dbg.vt_avail_ovr", value)
                Misc.safeSetprop("persist.dbg.volte_avail_ovr", value)
                Misc.safeSetprop("persist.dbg.wfc_avail_ovr", value)
                Misc.safeSetprop("persist.dbg.allow_ims_off", value)
            }
        }
    }

    override fun startup(ctxt: Context) {
        if (!ImsSettings.enabled()) return

        val sp = PreferenceManager.getDefaultSharedPreferences(ctxt)
        sp.registerOnSharedPreferenceChangeListener(spListener)

        this.ctxt = WeakReference(ctxt.applicationContext)

        //Refresh parameters on boot
        spListener.onSharedPreferenceChanged(sp, ImsSettings.requestNetwork)
    }
}
