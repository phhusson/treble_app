package me.phh.treble.app

import android.os.Environment
import android.util.Log
import java.io.File

object UpdateApplier {
    fun getLogs(): String {
        val file = File("/cache/phh/logs")
        return file.
                inputStream().
                bufferedReader().
                use { it.readText() }

    }

    fun applyUpdate(path: String) {
        val cachePhh = File("/cache/phh")
        val blockMap = File(cachePhh, "block.map")
        val flash = File(cachePhh, "fake-flash")

        cachePhh.deleteRecursively()

        val file = File(path).canonicalFile
        val origPath = file.canonicalPath
        val extStoragePath = Environment.getExternalStorageDirectory().canonicalPath
        if(origPath.startsWith(extStoragePath)) {
            //FIXME: How to find proper path?
            return applyUpdate(origPath.replace(extStoragePath, "/data/media/0"))
        }
        if(!origPath.startsWith("/data"))
            throw Exception("Updates are supported only from /data")
        cachePhh.mkdir()
        val process = Runtime.getRuntime().exec(arrayOf("uncrypt", origPath, blockMap.absolutePath))
        val res = process.waitFor()

        Log.d("PHH", "Uncrypt returned $res")
        if(res != 0 ) {
            val txt = process.inputStream.bufferedReader().use { it .readText() }
            val txt2 = process.errorStream.bufferedReader().use { it.readText() }
            Log.d("PHH", "Uncrypt failed $txt/$txt2")
            return
        }
        if(!blockMap.exists()) {
            Log.d("PHH", "Uncrypt suceeded, but no block.map")
            return
        }
        flash.outputStream().bufferedWriter().use { it.newLine() }

    }
}