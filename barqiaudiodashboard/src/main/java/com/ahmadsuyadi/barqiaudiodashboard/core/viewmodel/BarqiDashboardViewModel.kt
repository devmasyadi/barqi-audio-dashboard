package com.ahmadsuyadi.barqiaudiodashboard.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ahmadsuyadi.barqiaudiodashboard.core.data.BarqiRepository
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Playlist

class BarqiDashboardViewModel(private val barqiRepository: BarqiRepository) : ViewModel() {
    fun getInfoApp() = barqiRepository.getInfoApp().asLiveData()
    fun getArtists() = barqiRepository.getArtists().asLiveData()
    fun getAudios() = barqiRepository.getAudios().asLiveData()
    fun increaseView(idAudio: String?) = barqiRepository.increaseView(idAudio)
    fun getArtistsV2() = barqiRepository.getArtistsV2().asLiveData()
    fun getAudiosTrending(limit: Int?) = barqiRepository.getAudiosTrending(limit).asLiveData()
    fun getAudiosRecent(limit: Int? = 0) = barqiRepository.getAudiosRecent(limit).asLiveData()
    fun getLatestUpload(limit: Int? = 0) = barqiRepository.getLatestUpload(limit).asLiveData()
    fun searchAudio(nameAudio: String) = barqiRepository.searchAudio(nameAudio).asLiveData()
    fun getAudioByIdArtist(idArtist: String) = barqiRepository.getAudioByIdArtist(idArtist).asLiveData()
    fun addAudioToFavorite(audio: Audio) = barqiRepository.addAudioToFavorite(audio).asLiveData()
    fun removeAudioFromFavorite(idAudio: Int) = barqiRepository.removeAudioFromFavorite(idAudio).asLiveData()
    fun getFavoriteAudios() = barqiRepository.getFavoriteAudios().asLiveData()
    fun isAudioFavorite(idAudio: String) = barqiRepository.isAudioFavorite(idAudio).asLiveData()
    fun setRecentPlayedAudio(audio: Audio) = barqiRepository.setRecentPlayedAudio(audio)
    fun getRecentPlayedAudios() = barqiRepository.getRecentPlayedAudios().asLiveData()
    fun deleteAllRecentPlayed() = barqiRepository.deleteAllRecentPlayed().asLiveData()
    fun addToDownload(audio: Audio) = barqiRepository.addToDownload(audio).asLiveData()
    fun updateAudioDownloadByReq(reqDownload: Long) = barqiRepository.updateAudioDownloadByReq(reqDownload)
    fun deleteFromDownload(idAudio: Int) = barqiRepository.deleteFromDownload(idAudio).asLiveData()
    fun getAudiosDownload() = barqiRepository.getAudiosDownload().asLiveData()
    fun addPlaylist(playlist: Playlist) = barqiRepository.addPlaylist(playlist).asLiveData()
    fun editPlaylist(playlist: Playlist) = barqiRepository.editPlaylist(playlist).asLiveData()
    fun deletePlaylist(idPlaylist: Int) = barqiRepository.deletePlaylist(idPlaylist).asLiveData()
    fun addAudioToPlaylist(playlistId: Int, audio: Audio) = barqiRepository.addAudioToPlaylist(playlistId, audio).asLiveData()
    fun deleteAudioPlaylist(playlistId: Int, audioId: Int) = barqiRepository.deleteAudioFromPlaylist(playlistId, audioId).asLiveData()
    fun getListPlaylist() = barqiRepository.getListPlaylist().asLiveData()
    fun getPlaylistAndAudios() = barqiRepository.getPlaylistAndAudios().asLiveData()
    fun getAudiosByPlaylist(playlistId: Int) = barqiRepository.getAudiosByPlaylist(playlistId).asLiveData()
    fun getDummyTest() = barqiRepository.getDataDummyTest().asLiveData()
}