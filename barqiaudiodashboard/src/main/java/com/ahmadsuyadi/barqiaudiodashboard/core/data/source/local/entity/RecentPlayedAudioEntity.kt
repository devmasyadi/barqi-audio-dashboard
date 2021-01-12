package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recentPlayed")
data class RecentPlayedAudioEntity(

        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "id")
        val id: Long? = 0,

        @ColumnInfo(name = "idByTimestamp")
        val idByTimestamp: Long? = 0,

        @ColumnInfo(name = "idAudio")
        val idAudio: String? = null,

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
        val lastView: String? = null
)