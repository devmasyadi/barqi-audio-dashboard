package com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion

import retrofit2.HttpException

fun Throwable.handleMessageError(): String {
    var message = this.message
    if (this is HttpException) {
        message = this.response()?.errorBody()?.string()
    }
    return message.toString()
}