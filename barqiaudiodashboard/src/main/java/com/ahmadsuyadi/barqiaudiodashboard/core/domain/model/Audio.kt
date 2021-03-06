package com.ahmadsuyadi.barqiaudiodashboard.core.domain.model

data class Audio(
    val id: String? = null,
    val idByTimestamp: Long? = null,
    val duration: String? = null,
    val image: String? = null,
    val lyric: String? = null,
    val artist: String? = null,
    val title: String? = null,
    val views: Int? = null,
    val url: String? = null,
    val lastView: String? = null
)

