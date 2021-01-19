package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.ArtistResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Artist

object ArtistMapper {
    fun mapResponsesToEntities(input: List<ArtistResponse>) =
        input.map {
            Artist(
                id = it.id,
                name = it.name,
                image = it.image
            )
        }
}