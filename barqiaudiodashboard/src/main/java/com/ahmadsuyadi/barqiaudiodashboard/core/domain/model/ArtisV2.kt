package com.ahmadsuyadi.barqiaudiodashboard.core.domain.model

data class ArtisV2(
    val id: String? = null,
    val image: String? = null,
    val name: String? = null,
    val audios: List<AudiosItem?>? = null,
    val totalAudio: Int? = null,
)

data class AudiosItem(
    val id: String? = null,
    val idByTimestamp: Long? = null,
    val duration: String? = null,
    val image: String? = null,
    val artist: String? = null,
    val title: String? = null,
    val views: Int? = null,
    val url: String? = null,
    val lastView: String? = null,
    val lyric: String? = null
)

