package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.RecentPlayedAudioEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio

object RecentPlayedAudioMapper {
    fun mapAudioToEntity(input: Audio) =
        RecentPlayedAudioEntity(
            id = input.id,
            title = input.title,
            duration = input.duration,
            views = input.views,
            lyric = input.lyric,
            url = input.url,
            artist = input.artist,
            lastView = input.lastView,
            image = input.image
        )

    fun mapEntitiesToAudios(input: List<RecentPlayedAudioEntity>) =
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