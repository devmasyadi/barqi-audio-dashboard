package com.ahmadsuyadi.barqiaudiodashboard.core.utils

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.core.app.ActivityCompat
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion.validateTitleDownload
import java.io.File
import java.util.*

object Utils {

    private const val MULTIPLE_PERMISSIONS = 10

    private val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    fun checkPermissions(activity: Activity): Boolean {
        var result: Int
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        for (permission in permissions) {
            result = ActivityCompat.checkSelfPermission(activity, permission)
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission)
            }
        }
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                activity,
                listPermissionsNeeded.toTypedArray(),
                MULTIPLE_PERMISSIONS
            )
            return false
        }
        return true
    }

    fun downloadAudio(context: Context, audio: Audio): Long {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(audio.url))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setAllowedOverRoaming(false)
        request.setTitle("Download ${audio.title}.mp3")
        request.setVisibleInDownloadsUi(true)
        val file = File(
            Environment.getExternalStorageDirectory(),
            "/${context.packageName}/${audio.title?.validateTitleDownload()}.mp3"
        )
        request.setDestinationUri(Uri.fromFile(file))
        return downloadManager.enqueue(request)
    }
}