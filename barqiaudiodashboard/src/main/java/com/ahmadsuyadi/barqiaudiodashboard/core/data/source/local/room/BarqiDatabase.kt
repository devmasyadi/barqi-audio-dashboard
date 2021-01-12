package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.FavoriteAudioEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.RecentPlayedAudioEntity

@Database(
        entities = [
            FavoriteAudioEntity::class,
            RecentPlayedAudioEntity::class
        ], version = 1, exportSchema = false
)
abstract class BarqiDatabase : RoomDatabase() {
    abstract fun favoriteAudioDao(): FavoriteAudioDao
    abstract fun recentPlayedAudioDao(): RecentPlayedAudioDao
}