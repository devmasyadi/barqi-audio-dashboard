package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Playlist

@Entity(tableName = "playlist")
data class PlaylistEntity(
    @PrimaryKey
    val playlistId: Int? = null,
    val playlistName: String? = null,
    val playlistImage: String? = null,
)

