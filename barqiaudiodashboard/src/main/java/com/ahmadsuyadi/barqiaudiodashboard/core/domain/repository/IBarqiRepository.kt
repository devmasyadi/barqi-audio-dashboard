package com.ahmadsuyadi.barqiaudiodashboard.core.domain.repository

import com.ahmadsuyadi.barqiaudiodashboard.core.data.Resource
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.PlaylistAudioCrossRef
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.*
import kotlinx.coroutines.flow.Flow

interface IBarqiRepository {
    fun getInfoApp(): Flow<Resource<InfoApp>>
    fun getArtists(): Flow<Resource<List<Artist>>>
    fun getAudios(): Flow<Resource<List<Audio>>>
    fun increaseView(idAudio: String?)
    fun getAudiosTrending(limit: Int?): Flow<Resource<List<Audio>>>
    fun playAudioGl(audio: Audio): Flow<Resource<Audio>>
    fun searchGl(keyword: String): Flow<Resource<List<Audio>>>
    fun getTrendingGl(): Flow<Resource<List<Audio>>>
    fun getLatestGl(): Flow<Resource<List<Audio>>>
    fun getAudiosRecent(limit: Int? = 0): Flow<Resource<List<Audio>>>
    fun getLatestUpload(limit: Int? = 0): Flow<Resource<List<Audio>>>
    fun searchAudio(nameAudio: String): Flow<Resource<List<Audio>>>
    fun getAudioByIdArtist(idArtist: String): Flow<Resource<List<Audio>>>
    fun addAudioToFavorite(audio: Audio): Flow<Resource<Boolean>>
    fun removeAudioFromFavorite(idAudio: Int): Flow<Resource<Boolean>>
    fun getFavoriteAudios(): Flow<Resource<List<Audio>>>
    fun isAudioFavorite(idAudio: String): Flow<Boolean>
    fun setRecentPlayedAudio(audio: Audio)
    fun getRecentPlayedAudios(): Flow<Resource<List<Audio>>>
    fun deleteAllRecentPlayed(): Flow<Resource<Boolean>>
    fun addToDownload(audio: Audio): Flow<Resource<Boolean>>
    fun updateAudioDownloadByReq(idReqDownload: Long)
    fun deleteFromDownload(audio: Audio): Flow<Resource<Boolean>>
    fun getAudiosDownload(): Flow<Resource<List<Audio>>>
    fun addPlaylist(playlist: Playlist): Flow<Resource<Long>>
    fun editPlaylist(playlist: Playlist): Flow<Resource<Boolean>>
    fun deletePlaylist(idPlaylist: Int): Flow<Resource<Boolean>>
    fun addAudioToPlaylist(playlistId: Int, audio: Audio): Flow<Resource<Boolean>>
    fun deleteAudioFromPlaylist(playlistId: Int, audioId: Int): Flow<Resource<Boolean>>
    fun getListPlaylist(): Flow<Resource<List<Playlist>>>
    fun getPlaylistAndAudios(): Flow<Resource<List<PlaylistAndAudios>>>
    fun getAudiosByPlaylist(idPlaylist: Int): Flow<Resource<List<Audio>>>
    fun getDataDummyTest(): Flow<Resource<List<PlaylistAudioCrossRef>>>
    fun setCurrentPlaying(audio: Audio)
    fun getCurrentPlaying(): Flow<Resource<Audio>>
    fun addRequestAudio(data: RequestedAudio): Flow<Resource<Boolean>>
    fun getRequestedAudios(): Flow<Resource<List<RequestedAudio>>>
}