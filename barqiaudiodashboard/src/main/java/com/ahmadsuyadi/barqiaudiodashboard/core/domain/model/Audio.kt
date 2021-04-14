package com.ahmadsuyadi.barqiaudiodashboard.core.domain.model

data class Audio(
    val id: Int? = null,
    val duration: String? = null,
    val image: String? = null,
    val lyric: String? = null,
    val artist: String? = null,
    val title: String? = null,
    val views: Int? = null,
    var url: String? = null,
    val lastView: String? = null,
    var reqDownloaded: Long? = null,
    var idServer: String? = null,
)

