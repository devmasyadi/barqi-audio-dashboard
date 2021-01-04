package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.network

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.AdsResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.ArtistResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.AudioResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/apps/ads")
    fun getAds(
        @Query("packageName") packageName: String
    ): Flowable<AdsResponse>

    @GET("api/apps/artist")
    fun getArtists(
        @Query("packageName") packageName: String
    ): Flowable<List<ArtistResponse>>

    @GET("api/apps/audios")
    fun getAudios(
        @Query("packageName") packageName: String
    ): Flowable<List<AudioResponse>>
}