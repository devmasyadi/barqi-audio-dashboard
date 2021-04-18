package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist")
data class PlaylistEntity(
    @PrimaryKey
    val playlistId: Int? = null,
    val playlistName: String? = null,
    val playlistImage: String? = null,
)

