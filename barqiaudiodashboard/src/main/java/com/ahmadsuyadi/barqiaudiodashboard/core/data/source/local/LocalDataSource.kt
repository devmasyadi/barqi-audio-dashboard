package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.*
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.room.AudioDao

class LocalDataSource(
    private val audioDao: AudioDao,
) {
    //Audio
    suspend fun insertAudio(audioEntity: AudioEntity) = audioDao.insertAudio(audioEntity)
    suspend fun deleteAudioById(idFavoriteAudio: String) = audioDao.deleteAudioById(idFavoriteAudio)
    fun getListAudio() = audioDao.getListAudio()

    //FavoriteAudio
    suspend fun insertFavorite(data: FavoriteAudioEntity) = audioDao.insertFavorite(data)
    suspend fun deleteFavoriteByIdAudio(idFavoriteAudio: Int) = audioDao.deleteFavoriteByIdAudio(idFavoriteAudio)
    fun getFavoriteByIdAudio(idFavoriteAudio: String) = audioDao.getFavoriteByIdAudio(idFavoriteAudio)
    fun getListFavorite() = audioDao.getListFavorite()

    //RecentAudio
    suspend fun insertRecent(data: RecentAudioEntity) = audioDao.insertRecent(data)
    suspend fun deleteAllRecent() = audioDao.deleteAllRecent()
    fun getListRecent() = audioDao.getListRecent()

    //DownloadedAudio
    suspend fun insertDownloaded(data: DownloadedAudioEntity) = audioDao.insertDownloaded(data)
    suspend fun updateDownloadedByReq(reqDownload: Long) = audioDao.updateDownloadedByReq(reqDownload)
    suspend fun deleteDownloadedByIdAudio(idDownloadedAudio: String) = audioDao.deleteDownloadedByIdAudio(idDownloadedAudio)
    fun getListDownloaded() = audioDao.getListDownloaded()

    //Playlist
    suspend fun insertPlaylist(data: PlaylistEntity) = audioDao.insertPlaylist(data)
    suspend fun updatePlaylist(data: PlaylistEntity) = audioDao.updatePlaylist(data)
    suspend fun deletePlaylist(playlistEntity: PlaylistEntity) = audioDao.deletePlaylist(playlistEntity)
    suspend fun addAudioToPlaylist(data: PlaylistAudioCrossRef) = audioDao.addAudiosToPlaylist(data)
    suspend fun deleteAudioFromPlaylist(data: PlaylistAudioCrossRef) = audioDao.deleteAudioFromPlaylist(data)
    suspend fun deleteAllAudioByPlaylist(playlistId: Int) = audioDao.deleteAllAudioByPlaylist(playlistId)
    fun getListPlaylist() = audioDao.getListPlaylist()
    fun getPlaylistAndAudios() = audioDao.getPlaylistAndAudios()
    fun getAudiosByPlaylist(playlistId: Int) = audioDao.getAudiosByPlaylist(playlistId)
    fun getPlaylistAudioCrossRef() = audioDao.getPlaylistAudioCrossRef()

    //currentPlaying
    suspend fun setCurrentPlaying(data: CurrentPlayingEntity) =  audioDao.setCurrentPlaying(data)
    fun getCurrentPlaying() = audioDao.getCurrentPlaying()
}