package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.network

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.ArtistResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.ArtistV2Response
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.AudioResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.InfoAppResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface ApiService {
    @GET("api/apps/info")
    suspend fun getInfoApp(
        @Query("packageName") packageName: String
    ): InfoAppResponse

    @GET("api/apps/artist")
    suspend fun getArtists(
        @Query("packageName") packageName: String
    ): List<ArtistResponse>

    @GET("api/apps/audios")
    suspend fun getAudios(
        @Query("packageName") packageName: String
    ): List<AudioResponse>

    @PATCH("api/musics/counterViews")
    suspend fun increaseView(
        @Query("id") idAudio: String
    ): ResponseBody

    @GET("api/apps/artistV2")
    suspend fun getArtistsV2(
        @Query("packageName") packageName: String
    ): List<ArtistV2Response>

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

}