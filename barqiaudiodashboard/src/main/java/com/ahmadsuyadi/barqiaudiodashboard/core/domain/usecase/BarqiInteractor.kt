package com.ahmadsuyadi.barqiaudiodashboard.core.domain.usecase

import com.ahmadsuyadi.barqiaudiodashboard.core.data.Resource
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Ads
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Artist
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.repository.IBarqiRepository
import io.reactivex.Flowable

class BarqiInteractor (private val iBarqiRepository: IBarqiRepository): BarqiUseCase {
    override fun getAds() = iBarqiRepository.getAds()
    override fun getArtists() = iBarqiRepository.getArtists()
    override fun getAudios() = iBarqiRepository.getAudios()
    override fun increaseView(idAudio: String) = iBarqiRepository.increaseView(idAudio)
}