package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteAudio")
data class FavoriteAudioEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "_id")
    val id: String? = null,

    @ColumnInfo(name = "duration")
    val duration: String? = null,

    @ColumnInfo(name = "image")
    val image: String? = null,

    @ColumnInfo(name = "lyric")
    val lyric: String? = null,

    @ColumnInfo(name = "artist")
    val artist: String? = null,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "views")
    val views: Int? = null,

    @ColumnInfo(name = "url")
    val url: String? = null,

    @ColumnInfo(name = "last_view")
    val lastView: String? = null,
)