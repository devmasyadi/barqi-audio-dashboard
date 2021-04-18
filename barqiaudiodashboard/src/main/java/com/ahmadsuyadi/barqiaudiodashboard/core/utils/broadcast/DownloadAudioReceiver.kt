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
        info("Hallo on download Success BroadcastReceiver: $reqDownload")
        intentDownload.putExtra("A", reqDownload)
        AddDownloadToDbIntentService.enqueueWork(context, intentDownload)
    }

    private fun moveFileDownload(context: Context) {
        val file = File(Environment.getExternalStorageDirectory(), "/${context.packageName}/")
        file.listFiles().forEach {
            try {
                it.copyTo(File("${context.applicationInfo.dataDir}/${it.name}"), true)
                it.delete()
            } catch (e: Exception) {
                it.delete()
            }
        }
    }
}