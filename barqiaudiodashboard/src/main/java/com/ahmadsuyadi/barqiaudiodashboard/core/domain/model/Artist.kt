package com.ahmadsuyadi.barqiaudiodashboard.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist(
    val image: String? = null,
    val name: String? = null,
    val id: String? = null
) : Parcelable

