package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity

import androidx.room.Entity

@Entity(primaryKeys = ["playlistId", "audioId"])
data class PlaylistAudioCrossRef(
    val playlistId: Int,
    val audioId: Int
)
