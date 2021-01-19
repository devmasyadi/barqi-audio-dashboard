package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ArtistResponse(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)
