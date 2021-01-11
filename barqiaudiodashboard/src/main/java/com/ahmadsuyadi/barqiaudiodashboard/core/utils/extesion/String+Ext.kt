package com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion

import android.content.Context
import android.content.Intent
import android.net.Uri

fun String.gotoUrl(context: Context) {
    val uri = Uri.parse(this)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
}