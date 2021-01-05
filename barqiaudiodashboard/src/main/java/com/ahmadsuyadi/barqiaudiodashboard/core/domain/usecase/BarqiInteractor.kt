package com.ahmadsuyadi.barqiaudiodashboard.core.domain.usecase

import com.ahmadsuyadi.barqiaudiodashboard.core.domain.repository.IBarqiRepository

class BarqiInteractor(private val iBarqiRepository: IBarqiRepository) : BarqiUseCase {
    override fun getAds() = iBarqiRepository.getAds()
    override fun getArtists() = iBarqiRepository.getArtists()
    override fun getAudios() = iBarqiRepository.getAudios()
    override fun increaseView(idAudio: String) = iBarqiRepository.increaseView(idAudio)
    override fun getArtistsV2() = iBarqiRepository.getArtistsV2()
    override fun getAudiosTrending(limit: Int?) = iBarqiRepository.getAudiosTrending(limit)
    override fun getAudiosRecent(limit: Int?) = iBarqiRepository.getAudiosRecent(limit)
}