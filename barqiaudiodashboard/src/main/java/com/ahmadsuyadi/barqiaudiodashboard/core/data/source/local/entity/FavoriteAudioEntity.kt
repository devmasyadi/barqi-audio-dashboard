package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteAudio")
data class FavoriteAudioEntity(
    @PrimaryKey
    val favoriteAudioId: Int? = null,
    @Embedded
    val audio: DataAudioEntity
)
