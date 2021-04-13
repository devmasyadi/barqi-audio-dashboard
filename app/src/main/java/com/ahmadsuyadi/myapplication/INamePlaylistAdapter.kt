package com.ahmadsuyadi.myapplication

import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Playlist

interface INamePlaylistAdapter {
    fun onItemClicked(data: Playlist)
}