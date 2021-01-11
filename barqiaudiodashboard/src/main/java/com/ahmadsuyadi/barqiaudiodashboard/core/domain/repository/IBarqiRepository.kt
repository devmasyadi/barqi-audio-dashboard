package com.ahmadsuyadi.barqiaudiodashboard.core.domain.repository

import com.ahmadsuyadi.barqiaudiodashboard.core.data.Resource
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.ArtisV2
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Artist
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.InfoApp
import io.reactivex.Flowable

interface IBarqiRepository {
    fun getInfoApp(): Flowable<Resource<InfoApp>>
    fun getArtists(): Flowable<Resource<List<Artist>>>
    fun getAudios(): Flowable<Resource<List<Audio>>>
    fun increaseView(idAudio: String)
    fun getArtistsV2(): Flowable<Resource<List<ArtisV2>>>
    fun getAudiosTrending(limit: Int?): Flowable<Resource<List<Audio>>>
    fun getAudiosRecent(limit: Int? = 0): Flowable<Resource<List<Audio>>>
    fun getLatestUpload(limit: Int? = 0): Flowable<Resource<List<Audio>>>
    fun searchAudio(nameAudio: String): Flowable<Resource<List<Audio>>>
    fun getAudioByIdArtist(idArtist: String): Flowable<Resource<List<Audio>>>
    fun addAudioToFavorite(audio: Audio)
    fun removeAudioFromFavorite(idAudio: String)
    fun getFavoriteAudios(): Flowable<List<Audio>>
    fun isAudioFavorite(idAudio: String): Flowable<Boolean>
    fun setRecentPlayedAudio(audio: Audio)
    fun getRecentPlayedAudios(): Flowable<List<Audio>>
}