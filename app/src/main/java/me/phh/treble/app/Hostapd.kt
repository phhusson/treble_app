package me.phh.treble.app

import android.content.Context
import android.hardware.wifi.hostapd.V1_0.HostapdStatus
import android.hardware.wifi.hostapd.V1_0.HostapdStatusCode
import android.hardware.wifi.hostapd.V1_0.IHostapd
import android.os.SystemProperties
import android.util.Log
import java.io.File
import java.util.ArrayList

import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class Hostapd: EntryStartup {
    val hostapdSvc = object: android.hardware.wifi.hostapd.V1_0.IHostapd.Stub() {
        override fun terminate() {
            Log.d("PHH", "Terminating access point")
            SystemProperties.set("ctl.stop", "hostapd")
        }

        override fun removeAccessPoint(ifaceName: String): HostapdStatus {
            Log.d("PHH", "Stopping access point")
            val ret = HostapdStatus()
            ret.code = HostapdStatusCode.SUCCESS
            SystemProperties.set("ctl.stop", "hostapd")
            return ret
        }

        fun stringToHex(str: ArrayList<Byte>) =
            str
                    .toByteArray()
                    .joinToString(separator = "") {
                        it
                                .toInt()
                                .and(0xff)
                                .toString(16)
                                .padStart(2, '0')
                    }

        fun generatePsk(ssid: ArrayList<Byte>, passphrase: String): String {
            val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            val keySpec = PBEKeySpec(passphrase.toCharArray(), ssid.toByteArray(), 4096, 256)
            val secretKey = secretKeyFactory.generateSecret(keySpec)
            val bytes = secretKey.encoded

            val list = ArrayList<Byte>(bytes.size)
            for (b in bytes) {
                 list.add(b)
            }
            return stringToHex(list)
        }

        override fun addAccessPoint(ifaceParams: IHostapd.IfaceParams, nwParams: IHostapd.NetworkParams): HostapdStatus {
            Log.d("PHH", "Hostapd add access point")
            val ret = HostapdStatus()
            ret.code = HostapdStatusCode.FAILURE_UNKNOWN

            val ssidHex = stringToHex(nwParams.ssid)
            var encryptionConfig = ""
            if (nwParams.encryptionType != IHostapd.EncryptionType.NONE) {
                val psk = generatePsk(nwParams.ssid, nwParams.pskPassphrase)

                if (psk == "") {
                    ret.debugMessage = "Failed generating psk"
                    return ret
                }
                when(nwParams.encryptionType) {
                    IHostapd.EncryptionType.WPA ->
                        encryptionConfig = "wpa=3\nwpa_pairwise=TKIP CCMP\nwpa_psk=$psk\n"
                    IHostapd.EncryptionType.WPA2 ->
                        encryptionConfig = "wpa=2\nrsn_pairwise=CCMP\nwpa_psk=$psk\n"
                    else -> {
                        ret.debugMessage = "Unknown encryption type ${nwParams.encryptionType}"
                    }
                }
            }

            val hw_mode = if(ifaceParams.channelParams.band == IHostapd.Band.BAND_5_GHZ) "a" else "g"
            val channel = ifaceParams.channelParams.channel
            val hidden = if(nwParams.isHidden) "1" else "0"
            val config =
                    //"interface=${ifaceParams.ifaceName}\n" +
                    "interface=wlan0\n" +
                            "driver=nl80211\n" +
                            "ctrl_interface=/data/misc/wifi/hostapd/ctrl\n" +
                            "ssid2=${ssidHex}\n" +
                            "channel=$channel\n" +
                            "ieee80211n=1\n" +
                            "hw_mode=$hw_mode\n" +
                            "ignore_broadcast_ssid=$hidden\n" +
                            "wowlan_triggers=any\n" +
                            encryptionConfig

            try {
                val conf = File("/data/misc/wifi/hostapd.conf")
                conf.delete()
                conf.printWriter().use { it.print(config) }
                conf.setReadable(true, false)
            } catch (t: Throwable) {
                Log.d("PHH", "Failed setting hostapd config file", t)
            }
            SystemProperties.set("ctl.start", "hostapd")
            ret.code = HostapdStatusCode.SUCCESS
            return ret
        }
    }

    override fun startup(ctxt: Context) {
        Log.d("PHH", "Registering hostapd")
        hostapdSvc.registerAsService("default")
    }

    companion object : EntryStartup{
        override fun startup(ctxt: Context) {
            if(!SystemProperties.getBoolean("persist.sys.phh.system_hostapd", false)) return
            Log.d("PHH", "Implementing our own hostapd service")
            Hostapd().startup(ctxt)
        }
    }
}
