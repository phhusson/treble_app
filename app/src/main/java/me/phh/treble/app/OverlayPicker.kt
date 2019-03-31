package me.phh.treble.app

import android.content.Context
import android.os.ServiceManager
import android.content.om.IOverlayManager
import android.os.RemoteException
import android.os.SystemProperties
import android.util.Log

object OverlayPicker: EntryStartup {
    private var om: IOverlayManager? = null

    private val platform = SystemProperties.get("ro.board.platform")
    private val vendorFp = SystemProperties.get("ro.vendor.build.fingerprint")
    private val productBoard = SystemProperties.get("ro.product.board")

    private fun setOverlayEnabled(o: String, enabled: Boolean) {
        try {
            om!!.setEnabled(o, enabled, 0)
        } catch (e: RemoteException) {
            Log.d("PHH", "Failed to set overlay", e)
        }
    }

    private fun handleHtc(ctxt: Context) {
        //HTC U11+
        if (vendorFp == null) return

        if (vendorFp.contains("htc_ocm"))
            setOverlayEnabled("me.phh.treble.overlay.navbar", true)
    }

    private fun enableLte(ctxt: Context) {
        //TODO: List here all non-LTE platforms
        if ("mt6580" != platform)
            setOverlayEnabled("me.phh.treble.overlay.telephony.lte", true)
    }

    fun handleSamsung(ctxt: Context) {
        if(vendorFp == null) return

        if(vendorFp.matches(Regex(".*(crown|star)[q2]*lte.*")) ||
                vendorFp.matches(Regex(".*(SC-0[23]K|SCV3[89]).*"))) {
            setOverlayEnabled("me.phh.treble.overlay.samsung.s9.systemui", true)
        }
    }

    fun handleXiaomi(ctxt: Context) {
        if(vendorFp == null) return

        if(vendorFp.matches(Regex(".*iaomi/perseus.*"))) {
            setOverlayEnabled("me.phh.treble.overlay.xiaomi.mimix3.systemui", true)
        }
    }

    override fun startup(ctxt: Context) {
        om = IOverlayManager.Stub.asInterface(
                ServiceManager.getService("overlay"))

        handleHtc(ctxt)
        enableLte(ctxt)
        handleSamsung(ctxt)
        handleXiaomi(ctxt)

        setOverlayEnabled("me.phh.treble.overlay.systemui.falselocks", true)
    }
}