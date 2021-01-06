package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ArtistV2Response(

        @field:SerializedName("image")
        val image: String? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("audios")
        val audios: List<AudiosItem?>? = null,

        @field:SerializedName("_id")
        val id: String? = null,

        @field:SerializedName("totalAudio")
        val totalAudio: Int? = null,

        )

data class AudiosItem(

        @field:SerializedName("duration")
        val duration: String? = null,

        @field:SerializedName("image")
        val image: String? = null,

        @field:SerializedName("artist")
        val artist: String? = null,

        @field:SerializedName("_id")
        val id: String? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("views")
        val views: Int? = null,

        @field:SerializedName("url")
        val url: String? = null,

        @field:SerializedName("last_view")
        val lastView: String? = null,

        @field:SerializedName("lyric")
        val lyric: String? = null
)
