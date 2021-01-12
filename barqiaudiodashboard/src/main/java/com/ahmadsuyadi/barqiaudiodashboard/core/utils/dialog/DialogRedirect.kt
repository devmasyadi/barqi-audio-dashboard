package com.ahmadsuyadi.barqiaudiodashboard.core.utils.dialog

import android.content.Context
import com.ahmadsuyadi.barqiaudiodashboard.R
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.ConfigBarqiAudioDashboard
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion.gotoUrl
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion.toTextHtml
import org.jetbrains.anko.alert

fun Context.checkRedirectApp(message: String?) {
    with(ConfigBarqiAudioDashboard) {
        if (isOnRedirect && urlRedirect.isNotEmpty()) {
            alert(
                    message ?: getString(R.string.msg_update_redirect).toTextHtml().toString(),
                    getString(R.string.update_info)
            ) {
                negativeButton(getString(R.string.later)) {}
                positiveButton(getString(R.string.update)) {
                    urlRedirect.gotoUrl(this@checkRedirectApp)
                }
                isCancelable = false
            }.show()
        }
    }
}