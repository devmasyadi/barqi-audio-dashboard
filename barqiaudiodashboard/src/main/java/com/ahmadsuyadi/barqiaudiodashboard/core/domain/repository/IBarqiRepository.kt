package com.ahmadsuyadi.barqiaudiodashboard.core.domain.repository

import com.ahmadsuyadi.barqiaudiodashboard.core.data.Resource
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Ads
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Artist
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import io.reactivex.Flowable

interface IBarqiRepository {
    fun getAds(): Flowable<Resource<Ads>>
    fun getArtists(): Flowable<Resource<List<Artist>>>
    fun getAudios(): Flowable<Resource<List<Audio>>>
}