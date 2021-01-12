package com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.Spanned

fun String.gotoUrl(context: Context) {
    val uri = Uri.parse(this)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
}

fun String.toTextHtml(): Spanned? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    else
        Html.fromHtml(this)
}