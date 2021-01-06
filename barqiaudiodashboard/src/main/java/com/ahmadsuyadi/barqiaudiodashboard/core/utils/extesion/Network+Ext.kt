package com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import retrofit2.HttpException

fun Throwable.handleMessageError(): String {
    var message = this.message
    if (this is HttpException) {
        message = this.response()?.errorBody()?.string()
    }
    return message.toString()
}

fun isNetworkAvailable(context: Context?): Boolean {
    if (context == null) return false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        capabilities?.let {
            with(it) {
                return when {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo?.isConnected == true
    }
    return false
}