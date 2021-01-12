package com.ahmadsuyadi.barqiaudiodashboard.core.utils.dialog

import android.content.Context
import com.ahmadsuyadi.barqiaudiodashboard.R
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.ConfigBarqiAudioDashboard
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion.gotoUrl
import org.jetbrains.anko.alert

fun Context.checkRedirectApp(message: String?) {
    with(ConfigBarqiAudioDashboard) {
        if (isOnRedirect && urlRedirect.isNotEmpty()) {
            alert(
                    message ?: getString(R.string.msg_update_redirect),
                    getString(R.string.update_info)
            ) {
                negativeButton(getString(R.string.no)) {}
                positiveButton(getString(R.string.ok)) {
                    urlRedirect.gotoUrl(this@checkRedirectApp)
                }
                isCancelable = false
            }.show()
        }
    }
}