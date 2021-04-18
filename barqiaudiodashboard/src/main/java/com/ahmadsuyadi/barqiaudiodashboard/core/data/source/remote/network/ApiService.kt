package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.network

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.ArtistResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.AudioResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.InfoAppResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.RequestedAudioResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.RequestedAudio
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {
    @GET("api/apps/info")
    suspend fun getInfoApp(
        @Query("packageName") packageName: String
    ): InfoAppResponse

    @GET("api/apps/artistV3")
    suspend fun getArtists(
        @Query("packageName") packageName: String
    ): List<ArtistResponse>

    @GET("api/apps/audios/artistHome")
    suspend fun getAudios(
        @Query("packageName") packageName: String
    ): List<AudioResponse>

    @PATCH("api/musics/counterViews")
    suspend fun increaseView(
        @Query("id") idAudio: String
    ): ResponseBody


    @GET("api/apps/audios/trending")
    suspend fun getAudiosTrending(
        @Query("packageName") packageName: String,
        @Query("limit") limit: Int? = 0
    ): List<AudioResponse>

    @GET("api/apps/audios/latest")
    suspend fun getLatestUpload(
        @Query("packageName") packageName: String,
        @Query("limit") limit: Int? = 0
    ): List<AudioResponse>

    @GET("api/apps/audios/recent")
    suspend fun getAudiosRecent(
        @Query("limit") limit: Int? = 0
    ): List<AudioResponse>

    @GET("api/musics/search")
    suspend fun searchAudio(
        @Query("nameAudio") nameAudio: String? = null
    ): List<AudioResponse>

    @GET("api/musics/byArtistId")
    suspend fun getByArtistID(
        @Query("artistId") artistID: String? = null
    ): List<AudioResponse>

    @POST("api/requestedAudio")
    suspend fun addRequestedAudio(
        @Query("packageName") packageName: String? = null,
        @Body requestedAudio: RequestedAudio? = null
    ): ResponseBody

    @GET("api/requestedAudio/byApp")
    suspend fun getRequestedAudio(
        @Query("packageName") packageName: String? = null
    ): List<RequestedAudioResponse>

}