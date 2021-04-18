package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.PlaylistEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Playlist

object PlaylistMapper {
    fun mapDomainToEntity(input: Playlist) =
        with(input) {
            PlaylistEntity(
                playlistId = playlistId,
                playlistName = playlistName,
                playlistImage = playlistImage,
            )
        }

    fun mapEntityToDomain(input: PlaylistEntity) =
        with(input) {
            Playlist(
                playlistId = playlistId,
                playlistName = playlistName,
                playlistImage = playlistImage,
            )
        }

    fun mapEntitiesToDomains(input: List<PlaylistEntity>) =
        input.map {
            with(it) {
                Playlist(
                    playlistId = playlistId,
                    playlistName = playlistName,
                    playlistImage = playlistImage,
                )
            }
        }

}