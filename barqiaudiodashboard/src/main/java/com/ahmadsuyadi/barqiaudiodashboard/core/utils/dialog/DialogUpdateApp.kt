package com.ahmadsuyadi.barqiaudiodashboard.core.utils.dialog

import android.app.Activity
import com.ahmadsuyadi.barqiaudiodashboard.R
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import org.jetbrains.anko.alert

fun Activity.checkUpdateApp() {
    val appUpdateManager = AppUpdateManagerFactory.create(this)
    val appUpdateInfoTask = appUpdateManager.appUpdateInfo
    appUpdateManager.registerListener {
        if (it.installStatus() == InstallStatus.DOWNLOADED) {
            alert(getString(R.string.msg_success_update), getString(R.string.update_success)) {
                isCancelable = false
                negativeButton(getString(R.string.later)) {}
                positiveButton(getString(R.string.update)) {
                    appUpdateManager.completeUpdate()
                }
            }.show()
        }
    }
    appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
        if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
            appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
        ) {
            appUpdateManager.startUpdateFlowForResult(
                appUpdateInfo,
                AppUpdateType.FLEXIBLE,
                this,
                200
            )
        }
    }
}