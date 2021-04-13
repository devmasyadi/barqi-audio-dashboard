package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.FavoriteAudioEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio

object FavoriteAudioMapper {
    fun mapAudioToEntity(input: Audio) =
        FavoriteAudioEntity(
            favoriteAudioId = input.id,
            audio = AudioMapper.mapDomainToEntity(input)
        )

    fun mapEntitiesToAudios(input: List<FavoriteAudioEntity>) =
        input.map {
            val audio = it.audio
            audio.dataAudioId = it.favoriteAudioId
            AudioMapper.mapEntityToDomain(audio)
        }

}