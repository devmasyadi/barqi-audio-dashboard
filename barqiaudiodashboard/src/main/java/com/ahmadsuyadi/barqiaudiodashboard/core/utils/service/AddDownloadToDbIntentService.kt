package com.ahmadsuyadi.barqiaudiodashboard.core.utils.service

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import com.ahmadsuyadi.barqiaudiodashboard.core.data.Resource
import com.ahmadsuyadi.barqiaudiodashboard.core.viewmodel.BarqiDashboardViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.koin.android.ext.android.inject

class AddDownloadToDbIntentService : JobIntentService(), AnkoLogger {

    private val barqiDashboardViewModel: BarqiDashboardViewModel by inject()

    companion object {
        private const val JOB_ID = 1000
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, AddDownloadToDbIntentService::class.java, JOB_ID, intent)
        }
    }


    override fun onHandleWork(intent: Intent) {
        val reqDownload = intent.getLongExtra("A", -1)
        GlobalScope.launch {
            barqiDashboardViewModel.updateAudioDownloadByReq(reqDownload).collect { resources ->
                when (resources) {
                    is Resource.Error -> info("Hallo downloadIntent error: ${resources.message}")
                    is Resource.Loading -> info("Hallo downloadIntent loading")
                    is Resource.Success -> {
                        info("Hallo downloadIntent success ${resources.data}")
                    }
                }
            }
        }
    }

}