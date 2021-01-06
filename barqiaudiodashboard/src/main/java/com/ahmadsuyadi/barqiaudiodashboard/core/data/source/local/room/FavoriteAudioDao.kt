package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.FavoriteAudioEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FavoriteAudioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteAudio(audioEntity: FavoriteAudioEntity): Completable

    @Query("DELETE FROM favoriteAudio WHERE _id = :idFavoriteAudio")
    fun deleteFavoriteAudioById(idFavoriteAudio: String)

    @Query("SELECT * FROM favoriteAudio")
    fun getFavoriteAudios(): Flowable<List<FavoriteAudioEntity>>

    fun isFavorite(idAudio: String): Flowable<List<FavoriteAudioEntity>>

}