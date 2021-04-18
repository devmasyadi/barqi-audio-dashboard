package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.RequestedAudioResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.RequestedAudio

object RequestedAudioMapper {
    fun mapResponsesToDomains (input: List<RequestedAudioResponse>) =
            input.map {
                with(it) {
                    RequestedAudio(
                            createdAt = createdAt,
                            titleAudioRequested = titleAudioRequested,
                            name = name,
                            id = id,
                            dateSolved = dateSolved,
                            email = email,
                            isComplete = isComplete,
                            updatedAt = updatedAt,
                    )
                }
            }
}