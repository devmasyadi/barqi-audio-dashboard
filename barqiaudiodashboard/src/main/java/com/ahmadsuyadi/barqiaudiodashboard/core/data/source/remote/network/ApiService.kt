package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.network

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.AdsResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.ArtistResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.ArtistV2Response
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.AudioResponse
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.PATCH
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

    @PATCH("api/musics/counterViews")
    fun increaseView(
        @Query("id") idAudio: String
    ): Flowable<ResponseBody>

    @GET("api/apps/artistV2")
    fun getArtistsV2(
        @Query("packageName") packageName: String
    ): Flowable<List<ArtistV2Response>>

    @GET("api/apps/audios/trending")
    fun getAudiosTrending(
        @Query("packageName") packageName: String,
        @Query("limit") limit: Int? = 0
    ): Flowable<List<AudioResponse>>

    @GET("api/apps/audios/recent")
    fun getAudiosRecent(
        @Query("limit") limit: Int? = 0
    ): Flowable<List<AudioResponse>>

    @GET("api/musics/search")
    fun searchAudio(
        @Query("nameAudio") nameAudio: String? = null
    ): Flowable<List<AudioResponse>>

    @GET("api/musics/byArtistId")
    fun getByArtistID(
        @Query("artistId") artistID: String? = null
    ): Flowable<List<AudioResponse>>

}