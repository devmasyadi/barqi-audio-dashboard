package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.*

@Database(
    entities = [
        AudioEntity::class,
        DownloadedAudioEntity::class,
        FavoriteAudioEntity::class,
        PlaylistAudioCrossRef::class,
        PlaylistEntity::class,
        RecentAudioEntity::class
    ], version = 3, exportSchema = false
)
abstract class BarqiDatabase : RoomDatabase() {
    abstract fun audioDao(): AudioDao
}