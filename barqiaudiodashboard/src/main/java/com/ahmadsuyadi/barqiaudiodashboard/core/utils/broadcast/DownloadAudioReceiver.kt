package com.ahmadsuyadi.barqiaudiodashboard.core.utils.broadcast

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Environment
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.service.AddDownloadToDbIntentService
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.io.File

class DownloadAudioReceiver : BroadcastReceiver(), AnkoLogger {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        moveFileDownload(context)
        val intentDownload = Intent(context, AddDownloadToDbIntentService::class.java)
        val reqDownload = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        intentDownload.putExtra(AddDownloadToDbIntentService.REQ_DOWNLOAD, reqDownload)
        AddDownloadToDbIntentService.enqueueWork(context, intentDownload)
    }

    private fun moveFileDownload(context: Context) {
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "/${context.packageName}/")
        file.listFiles()?.forEach {
            try {
                it.copyTo(File("${context.applicationInfo.dataDir}/${it.name}"), true)
                it.delete()
            } catch (e: Exception) {
                info("Hallo file error:${e.message}")
                it.delete()
            }
        }
    }
}