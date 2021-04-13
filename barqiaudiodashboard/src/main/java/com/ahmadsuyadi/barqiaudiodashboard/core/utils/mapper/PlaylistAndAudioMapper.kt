package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.PlaylistAndAudiosEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.PlaylistAndAudios

object PlaylistAndAudioMapper {
    fun mapEntitiesToDomains(input: List<PlaylistAndAudiosEntity>) =
            input.map {
                with(it) {
                    PlaylistAndAudios(
                            playlist = playlist?.let { value -> PlaylistMapper.mapEntityToDomain(value) },
                            audios = audios?.map { value ->
                                AudioMapper.mapEntityToDomain(value.audio)
                            }
                    )
                }
            }

}