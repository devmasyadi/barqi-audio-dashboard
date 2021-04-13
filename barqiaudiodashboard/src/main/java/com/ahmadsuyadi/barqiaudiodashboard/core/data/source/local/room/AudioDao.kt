package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.room

import androidx.room.*
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AudioDao {
    //Audio
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAudio(audioEntity: AudioEntity)

    @Query("DELETE FROM audio WHERE dataAudioId = :audioId")
    suspend fun deleteAudioById(audioId: String)

    @Query("SELECT * FROM audio")
    fun getListAudio(): Flow<List<AudioEntity>>

    //FavoriteAudio
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(data: FavoriteAudioEntity)

    @Query("DELETE FROM favoriteAudio WHERE dataAudioId = :idFavoriteAudio")
    suspend fun deleteFavoriteByIdAudio(idFavoriteAudio: Int)

    @Query("SELECT * FROM favoriteAudio WHERE dataAudioId = :idFavoriteAudio")
    fun getFavoriteByIdAudio(idFavoriteAudio: String): Flow<List<FavoriteAudioEntity>>

    @Query("SELECT * FROM favoriteAudio")
    fun getListFavorite(): Flow<List<FavoriteAudioEntity>>

    //RecentAudio
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecent(data: RecentAudioEntity)

    @Query("DELETE FROM recentAudio")
    suspend fun deleteAllRecent()

    @Query("SELECT * FROM recentAudio")
    fun getListRecent(): Flow<List<RecentAudioEntity>>

    //DownloadedAudio
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloaded(data: DownloadedAudioEntity)

    @Query("DELETE FROM downloadedAudio WHERE dataAudioId = :idDownloadedAudio")
    suspend fun deleteDownloadedByIdAudio(idDownloadedAudio: String)

    @Query("SELECT * FROM downloadedAudio")
    fun getListDownloaded(): Flow<List<DownloadedAudioEntity>>

    //Playlist
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(data: PlaylistEntity): Long

    @Update
    suspend fun updatePlaylist(data: PlaylistEntity)

    @Delete
    suspend fun deletePlaylist(playlistEntity: PlaylistEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAudiosToPlaylist(data: PlaylistAudioCrossRef)

    @Delete
    suspend fun deleteAudioFromPlaylist(data: PlaylistAudioCrossRef)

    @Query("DELETE FROM audio where  audioId= :playlistId ")
    suspend fun deleteAllAudioByPlaylist(playlistId: Int)

    @Query("SELECT * FROM playlist")
    fun getListPlaylist(): Flow<List<PlaylistEntity>>

//    @Query("SELECT * FROM dataPlaylistAndAudio LEFT JOIN playlist ON foreignPlaylistId=playlistId LEFT JOIN audio ON foreignAudioId=idAudio")
    @Transaction
    @Query("SELECT * FROM playlist")
    fun getPlaylistAndAudios(): Flow<List<PlaylistAndAudiosEntity>>

    @Query("SELECT audio.* FROM playlistaudiocrossref JOIN audio ON playlistaudiocrossref.audioId = audio.audioId WHERE playlistId= :idPlaylist ")
    fun getAudiosByPlaylist(idPlaylist: Int): Flow<List<AudioEntity>>

    @Query("SELECT * FROM playlistaudiocrossref")
    fun getPlaylistAudioCrossRef(): Flow<List<PlaylistAudioCrossRef>>

}