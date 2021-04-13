package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recentAudio")
data class RecentAudioEntity(
    @PrimaryKey
    val recentAudioId: Int? = null,
    @Embedded
    val audioEntity: DataAudioEntity
)
