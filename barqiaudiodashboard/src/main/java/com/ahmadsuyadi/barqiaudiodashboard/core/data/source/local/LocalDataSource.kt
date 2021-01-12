package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.FavoriteAudioEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.RecentPlayedAudioEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.room.FavoriteAudioDao
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.room.RecentPlayedAudioDao

class LocalDataSource(
        private val favoriteAudioDao: FavoriteAudioDao,
        private val recentPlayedAudioDao: RecentPlayedAudioDao
) {

    fun insertFavoriteAudio(audioEntity: FavoriteAudioEntity) =
            favoriteAudioDao.insertFavoriteAudio(audioEntity)

    fun deleteFavoriteAudioById(idFavoriteAudio: String) =
            favoriteAudioDao.deleteFavoriteAudioById(idFavoriteAudio)

    fun getFavoriteAudios() = favoriteAudioDao.getFavoriteAudios()
    fun isFavoriteAudio(idAudio: String) = favoriteAudioDao.isFavorite(idAudio)

    fun insertRecentPlayedAudio(recentPlayedAudioEntity: RecentPlayedAudioEntity) =
            recentPlayedAudioDao.insertRecentPlayedAudio(recentPlayedAudioEntity)

    fun deleteRecentPlayedAudio(recentPlayedAudioEntity: RecentPlayedAudioEntity) =
            recentPlayedAudioDao.deleteRecentPlayedAudio(recentPlayedAudioEntity)

    fun getRecentPlayedAudio() = recentPlayedAudioDao.getRecentPlayedAudio()

}