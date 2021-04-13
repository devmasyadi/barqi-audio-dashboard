package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity

import androidx.room.*


data class PlaylistAndAudiosEntity (
    @Embedded
    val playlist: PlaylistEntity,
    @Relation(
        parentColumn = "playlistId",
        entityColumn = "audioId",
        associateBy = Junction(PlaylistAudioCrossRef::class)
    )
    val audios: List<AudioEntity>,
)