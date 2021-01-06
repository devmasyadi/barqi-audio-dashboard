package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.room

import androidx.room.*
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.RecentPlayedAudioEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface RecentPlayedAudioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentPlayedAudio(recentPlayedAudioEntity: RecentPlayedAudioEntity): Completable

    @Delete
    fun deleteRecentPlayedAudio(recentPlayedAudioEntity: RecentPlayedAudioEntity)

    @Query("SELECT * FROM recentPlayed")
    fun getRecentPlayedAudio(): Flowable<List<RecentPlayedAudioEntity>>
}