package com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.Spanned
import java.io.File

fun String.gotoUrl(context: Context) {
    val uri = Uri.parse(this)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
}

@Suppress("DEPRECATION")
fun String.toTextHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    else
        Html.fromHtml(this)
}

fun String.toPathAudioDownload(context: Context): String {
    return "${context.applicationInfo.dataDir}/${this}.mp3"
}

fun String.toDeleteFile() {
    File(this).delete()
}

fun String.validateTitleDownload() = this.replace("/", "")
