package com.ahmadsuyadi.barqiaudiodashboard.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Playlist(
    val playlistId: Int? = null,
    val playlistName: String? = null,
    val playlistImage: String? = null,
) : Parcelable

@Parcelize
data class PlaylistAndAudios(
    val playlist: Playlist? = null,
    val audios: List<Audio>? = null,
) : Parcelable
