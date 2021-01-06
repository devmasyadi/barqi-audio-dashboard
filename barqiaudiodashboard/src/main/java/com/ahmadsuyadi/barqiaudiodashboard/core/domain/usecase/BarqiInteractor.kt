package com.ahmadsuyadi.barqiaudiodashboard.core.domain.usecase

import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.repository.IBarqiRepository

class BarqiInteractor(private val iBarqiRepository: IBarqiRepository) : BarqiUseCase {
    override fun getAds() = iBarqiRepository.getAds()
    override fun getArtists() = iBarqiRepository.getArtists()
    override fun getAudios() = iBarqiRepository.getAudios()
    override fun increaseView(idAudio: String) = iBarqiRepository.increaseView(idAudio)
    override fun getArtistsV2() = iBarqiRepository.getArtistsV2()
    override fun getAudiosTrending(limit: Int?) = iBarqiRepository.getAudiosTrending(limit)
    override fun getAudiosRecent(limit: Int?) = iBarqiRepository.getAudiosRecent(limit)
    override fun searchAudio(nameAudio: String) = iBarqiRepository.searchAudio(nameAudio)
    override fun getAudioByIdArtist(idArtist: String) =
            iBarqiRepository.getAudioByIdArtist(idArtist)

    override fun addAudioToFavorite(audio: Audio) = iBarqiRepository.addAudioToFavorite(audio)
    override fun removeAudioFromFavorite(idAudio: String) =
            iBarqiRepository.removeAudioFromFavorite(idAudio)

    override fun getFavoriteAudios() = iBarqiRepository.getFavoriteAudios()
    override fun isAudioFavorite(idAudio: String) = iBarqiRepository.isAudioFavorite(idAudio)
    override fun setRecentPlayedAudio(audio: Audio) = iBarqiRepository.setRecentPlayedAudio(audio)
    override fun getRecentPlayedAudios() = iBarqiRepository.getRecentPlayedAudios()
}