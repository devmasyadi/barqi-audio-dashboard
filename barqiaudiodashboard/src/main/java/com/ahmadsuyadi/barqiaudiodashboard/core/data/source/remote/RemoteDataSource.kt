package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.network.ApiService
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.RequestedAudio

class RemoteDataSource(
    private val apiService: ApiService
) {

    suspend fun getInfoApp(packageName: String) = apiService.getInfoApp(packageName)

    suspend fun getArtists(packageName: String) = apiService.getArtists(packageName)

    suspend fun getAudios(packageName: String) = apiService.getAudios(packageName)

    suspend fun increaseView(idAudio: String) = apiService.increaseView(idAudio)

    suspend fun getArtistsV2(packageName: String) = apiService.getArtistsV2(packageName)

    suspend fun getAudiosTrending(packageName: String, limit: Int?) =
        apiService.getAudiosTrending(packageName, limit)

    suspend fun getLatestUpload(packageName: String, limit: Int?) =
        apiService.getLatestUpload(packageName, limit)

    suspend fun getAudiosRecent(limit: Int?) = apiService.getAudiosRecent(limit)

    suspend fun searchAudio(nameAudio: String?) = apiService.searchAudio(nameAudio)

    suspend fun getAudioByArtistId(artistId: String?) = apiService.getByArtistID(artistId)

    suspend fun addRequestedAudio(packageName: String?, data: RequestedAudio?) =
        apiService.addRequestedAudio(packageName, data)

    suspend fun getRequestedAudio(packageName: String?) = apiService.getRequestedAudio(packageName)

}