package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audio")
data class AudioEntity(
    @PrimaryKey(autoGenerate = true)
    val audioId: Long? = null,
    @Embedded
    val audio: DataAudioEntity
)

data class DataAudioEntity(
    var dataAudioId: Int? = null,
    val duration: String? = null,
    val image: String? = null,
    val lyric: String? = null,
    val artist: String? = null,
    val title: String? = null,
    val views: Int? = null,
    val url: String? = null,
    val lastView: String? = null,
)