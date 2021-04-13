package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.DownloadedAudioEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio

object DownloadedAudioMapper {
    fun mapDomainToEntity(input: Audio) =
        DownloadedAudioEntity(
            downloadedAudioId = input.id,
            audio = AudioMapper.mapDomainToEntity(input),
            reqDownloadId = input.reqDownloaded
        )

    fun mapEntitiesToDomains(input: List<DownloadedAudioEntity>) =
        input.map {
            val audio = it.audio
            audio.dataAudioId = it.downloadedAudioId
            AudioMapper.mapEntityToDomain(audio, it.reqDownloadId)
        }

}