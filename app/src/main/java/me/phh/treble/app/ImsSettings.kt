package me.phh.treble.app

import android.app.AlertDialog
import android.app.Application
import android.app.DownloadManager
import android.content.*
import android.hardware.display.DisplayManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.os.UserHandle
import android.telephony.TelephonyManager
import android.util.Log
import androidx.core.content.FileProvider
import androidx.preference.ListPreference
import androidx.preference.Preference
import java.io.File

object ImsSettings : Settings {
    val requestNetwork = "key_ims_request_network"
    val createApn = "key_ims_create_apn"
    val forceEnableSettings = "key_ims_force_enable_setting"
    val installImsApk = "key_ims_install_apn"

    override fun enabled() = true
}

class ImsSettingsFragment : SettingsFragment() {
    override val preferencesResId = R.xml.pref_ims
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        super.onCreatePreferences(savedInstanceState, rootKey)

        val createApn = findPreference<Preference>(ImsSettings.createApn)
        createApn!!.setOnPreferenceClickListener {
            Log.d("PHH", "Adding \"ims\" APN")

            val tm = activity.getSystemService(TelephonyManager::class.java)
            val operator = tm.simOperator
            if(tm.simOperator == null || tm.simOperator == "") {
                Log.d("PHH","No current carrier bailing out")
                return@setOnPreferenceClickListener true
            }

            val mcc = operator.substring(0, 3)
            val mnc = operator.substring(3, operator.length)
            Log.d("PHH", "Got mcc = $mcc and mnc = $mnc")

            val cr = activity.contentResolver

            val cursor = cr.query(
                    Uri.parse("content://telephony/carriers/current"),
                    arrayOf("name", "type", "apn", "carrier_enabled", "edited"),
                    "name = ?", arrayOf("PHH IMS"), null
            )

            if(cursor != null && cursor.moveToFirst()) {
                Log.d("PHH", "PHH IMS APN for this provider is already here with data $cursor")
                return@setOnPreferenceClickListener true
            }

            Log.d("PHH", "No APN called PHH IMS, adding our own")

            val cv = ContentValues()
            cv.put("name", "PHH IMS")
            cv.put("apn", "ims")
            cv.put("type", "ims")
            cv.put("edited", "1")
            cv.put("user_editable", "1")
            cv.put("user_visible", "1")
            cv.put("protocol", "IPV4V6")
            cv.put("roaming_protocol", "IPV6")
            cv.put("modem_cognitive", "1")
            cv.put("numeric", operator)
            cv.put("mcc", mcc)
            cv.put("mnc", mnc)

            val res = cr.insert(Uri.parse("content://telephony/carriers"), cv)
            Log.d("PHH", "Insert APN returned $res")

            return@setOnPreferenceClickListener true
        }

        val installIms = findPreference<Preference>(ImsSettings.installImsApk)
        val hidlService = android.hidl.manager.V1_0.IServiceManager.getService()


        val allSlots = listOf("imsrild1", "imsrild2", "imsrild3", "slot1", "slot2", "slot3", "imsSlot1", "imsSlot2", "mtkSlot1", "mtkSlot2", "imsradio0", "imsradio1")
        val gotMtkPie = allSlots
                .find { i -> hidlService.get("vendor.mediatek.hardware.radio@3.0::IRadio", i) != null } != null
        val gotMtkQuack = allSlots
                .find { i -> hidlService.get("vendor.mediatek.hardware.mtkradioex@1.0::IMtkRadioEx", i) != null } != null
        val gotQualcomm = allSlots
                .find { i -> hidlService.get("vendor.qti.hardware.radio.ims@1.0::IImsRadio", i) != null } != null

        Log.d("PHH", "MTK Pie radio = $gotMtkPie")
        Log.d("PHH", "MTK Quack radio = $gotMtkQuack")
        Log.d("PHH", "Qualcomm radio = $gotQualcomm")

        val (url, message) =
                when {
                    gotMtkPie -> Pair("https://treble.phh.me/stable/ims-mtk-p.apk", "Mediatek Pie vendor")
                    gotMtkQuack -> Pair("https://treble.phh.me/stable/ims-mtk-q.apk", "Mediatek Pie vendor")
                    gotQualcomm -> Pair("https://treble.phh.me/stable/ims-q.64.apk", "Qualcomm vendor")
                    else -> Pair("", "NOT SUPPORTED")
                }

        installIms!!.title = "Install IMS APK for $message"
        installIms!!.setOnPreferenceClickListener {
            val dm = activity.getSystemService(DownloadManager::class.java)

            val downloadRequest = DownloadManager.Request(Uri.parse(url))
            downloadRequest.setTitle("IMS APK")
            downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

            downloadRequest.setDestinationInExternalFilesDir(activity, Environment.DIRECTORY_DOWNLOADS, "ims.apk")

            val myId = dm!!.enqueue(downloadRequest)

            activity.registerReceiver(object: BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    Log.d("PHH", "Received download completed with intent $intent ${intent.data}")
                    if(intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L) != myId) return

                    val query = DownloadManager.Query().setFilterById(myId)
                    val cursor = dm.query(query)
                    if(!cursor.moveToFirst()) {
                        Log.d("PHH", "DownloadManager gave us an empty cursor")
                        return
                    }

                    val localUri = Uri.parse(cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)))
                    Log.d("PHH", "Got localURI = $localUri")
                    val filename = localUri.lastPathSegment

                    val providerUri = FileProvider.getUriForFile(activity, "me.phh.treble.app", File(localUri.path))
                    Log.d("PHH", "Got providerUri = $providerUri")

                    val i = Intent(Intent.ACTION_VIEW)
                    i.setDataAndType(providerUri, "application/vnd.android.package-archive")
                    i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    //context.startActivity(i)
                    val m = Context::class.java.getMethod("startActivityAsUser", Intent::class.java, UserHandle::class.java)

                    activity.grantUriPermission("android", providerUri, Intent.FLAG_GRANT_READ_URI_PERMISSION)

                    m.invoke(activity, i, UserHandle.getUserHandleForUid(10099))

                    activity.unregisterReceiver(this)
                }

            }, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

            return@setOnPreferenceClickListener true
        }
    }
}
