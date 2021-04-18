package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class PlaylistAndAudiosEntity(
    @Embedded
    val playlist: PlaylistEntity,
    @Relation(
        parentColumn = "playlistId",
        entityColumn = "audioId",
        associateBy = Junction(PlaylistAudioCrossRef::class)
    )
    val audios: List<AudioEntity>,
)