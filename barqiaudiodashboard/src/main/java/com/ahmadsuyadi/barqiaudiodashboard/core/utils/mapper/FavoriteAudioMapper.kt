package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.FavoriteAudioEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio

object FavoriteAudioMapper {
    fun mapAudioToEntity(input: Audio) =
            FavoriteAudioEntity(
                    idAudio = input.id,
                    title = input.title,
                    duration = input.duration,
                    views = input.views,
                    lyric = input.lyric,
                    url = input.url,
                    artist = input.artist,
                    lastView = input.lastView,
                    image = input.image
            )

    fun mapEntitiesToAudios(input: List<FavoriteAudioEntity>) =
            input.map {
                Audio(
                        id = it.idAudio,
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