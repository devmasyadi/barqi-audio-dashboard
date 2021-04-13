package com.ahmadsuyadi.myapplication

import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.PlaylistAndAudios

interface IPlaylistAdapter {
    fun onItemClicked(playlist: PlaylistAndAudios)
}