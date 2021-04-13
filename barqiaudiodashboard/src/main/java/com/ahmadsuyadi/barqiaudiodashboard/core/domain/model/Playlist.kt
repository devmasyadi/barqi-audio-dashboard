package com.ahmadsuyadi.barqiaudiodashboard.core.domain.model

data class Playlist(
    val playlistId: Int? = null,
    val playlistName: String? = null,
    val playlistImage: String? = null,
)

data class PlaylistAndAudios(
    val playlist: Playlist? = null,
    val audios: List<Audio>? = null,
)
