package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RequestedAudioResponse(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("titleAudioRequested")
    val titleAudioRequested: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("dateSolved")
    val dateSolved: String? = null,

    @field:SerializedName("dateRequest")
    val dateRequest: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("isComplete")
    val isComplete: Boolean? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)
