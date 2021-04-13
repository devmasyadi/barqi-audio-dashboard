package com.ahmadsuyadi.myapplication.utils

import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio

interface IPlaylistAudioAdapter {
    fun removeFromPlaylist(audio: Audio)
}