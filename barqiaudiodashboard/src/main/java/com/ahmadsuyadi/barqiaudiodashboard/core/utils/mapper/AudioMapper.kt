package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.AudioEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.DataAudioEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.AudioResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio

object AudioMapper {
    fun mapResponsesToDomains(input: List<AudioResponse>) =
        input.map {
            Audio(
                id = it.idByTimestamp?.toInt(),
                title = it.title,
                duration = it.duration,
                views = it.views,
                lyric = it.lyric,
                url = it.url,
                artist = it.artist,
                lastView = it.lastView,
                image = it.image,
                idServer = it.id,
                urlDownload = it.urlDownload
            )
        }

    fun mapResponseToDomain(input: AudioResponse) =
        with(input) {
            Audio(
                id = idByTimestamp?.toInt(),
                title = title,
                duration = duration,
                views = views,
                lyric = lyric,
                url = url,
                artist = artist,
                lastView = lastView,
                image = image,
                idServer = id,
                urlDownload = urlDownload
            )
        }

    fun mapDomainToEntity(input: Audio) =
        with(input) {
            DataAudioEntity(
                dataAudioId = input.id,
                title = title,
                duration = duration,
                views = views,
                lyric = lyric,
                url = url,
                artist = artist,
                lastView = lastView,
                image = image,
                idServer = idServer,
                urlDownload = urlDownload
            )
        }

    fun mapEntityToDomain(input: DataAudioEntity, reqDownloaded: Long? = -1) =
        with(input) {
            Audio(
                id = dataAudioId,
                title = title,
                duration = duration,
                views = views,
                lyric = lyric,
                url = url,
                artist = artist,
                lastView = lastView,
                image = image,
                reqDownloaded = reqDownloaded,
                idServer = idServer,
                urlDownload = urlDownload
            )
        }

    fun mapEntitiesToDomains(input: List<AudioEntity>) =
        input.map {
            mapEntityToDomain(it.audio)
        }

}