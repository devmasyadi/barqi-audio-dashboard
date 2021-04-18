package com.ahmadsuyadi.barqiaudiodashboard.core.utils.service

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import com.ahmadsuyadi.barqiaudiodashboard.core.viewmodel.BarqiDashboardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.AnkoLogger
import org.koin.android.ext.android.inject

class AddDownloadToDbIntentService : JobIntentService(), AnkoLogger {

    private val barqiDashboardViewModel: BarqiDashboardViewModel by inject()

    companion object {
        const val REQ_DOWNLOAD = "reqDownload"
        private const val JOB_ID = 1000
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, AddDownloadToDbIntentService::class.java, JOB_ID, intent)
        }
    }

    override fun onHandleWork(intent: Intent) {
        val reqDownload = intent.getLongExtra(REQ_DOWNLOAD, -1)
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                barqiDashboardViewModel.updateAudioDownloadByReq(
                    reqDownload
                )
            }
            /* barqiDashboardViewModel.updateAudioDownloadByReq(reqDownload).collect { resources ->
                 when (resources) {
                     is Resource.Error -> info("Hallo downloadIntent error: ${resources.message}")
                     is Resource.Loading -> info("Hallo downloadIntent loading")
                     is Resource.Success -> {
                         info("Hallo downloadIntent success ${resources.data}")
                     }
                 }
             }*/
        }
    }

}