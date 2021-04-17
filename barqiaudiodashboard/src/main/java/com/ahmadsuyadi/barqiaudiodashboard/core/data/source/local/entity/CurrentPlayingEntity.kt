package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currentPlaying")
data class CurrentPlayingEntity(
        @PrimaryKey
        val idCurrentPlayingEntity: Int = 1,
        @Embedded
        val audio: DataAudioEntity
)
