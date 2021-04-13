package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloadedAudio")
data class DownloadedAudioEntity(
    @PrimaryKey
    val downloadedAudioId: Int? = null,
    @Embedded
    val audio: DataAudioEntity
)
