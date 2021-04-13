package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloadedAudio")
data class DownloadedAudioEntity(
    @PrimaryKey
    val downloadedAudioId: Int? = null,
    val isDownloaded: Boolean? = false,
    val reqDownloadId: Long? = null,
    @Embedded
    val audio: DataAudioEntity
)
