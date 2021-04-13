package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.RecentAudioEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio

object RecentPlayedAudioMapper {
    fun mapAudioToEntity(input: Audio) =
        RecentAudioEntity(
            recentAudioId = input.id,
            audioEntity = AudioMapper.mapDomainToEntity(input)
        )

    fun mapEntitiesToAudios(input: List<RecentAudioEntity>) =
        input.map {
            val audio = it.audioEntity
            audio.dataAudioId = it.recentAudioId
            AudioMapper.mapEntityToDomain(audio)
        }
}