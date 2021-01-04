package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.AudioResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio

object AudioMapper {
    fun mapResponsesToDomains(input: List<AudioResponse>) =
        input.map {
            Audio(
                id = it.id,
                title = it.title,
                duration = it.duration,
                views = it.views,
                lyric = it.lyric,
                url = it.url,
                artist = it.artist,
                lastView = it.lastView,
                image = it.image
            )
        }
}