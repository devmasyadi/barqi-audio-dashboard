package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.ArtistV2Response
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.ArtisV2
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.AudiosItem

object ArtistV2Mapper {
    fun mapResponsesToDomains(input: List<ArtistV2Response>) =
            input.map {
                ArtisV2(
                        id = it.id,
                        image = it.image,
                        name = it.name,
                        totalAudio = it.totalAudio,
                        audios = it.audios?.map { item ->
                            AudiosItem(
                                    id = item?.id,
                                    idByTimestamp = item?.idByTimestamp,
                                    title = item?.title,
                                    duration = item?.duration,
                                    views = item?.views,
                                    lyric = item?.lyric,
                                    url = item?.url,
                                    artist = item?.artist,
                                    lastView = item?.lastView,
                                    image = item?.image
                            )
                        }
                )
            }
}