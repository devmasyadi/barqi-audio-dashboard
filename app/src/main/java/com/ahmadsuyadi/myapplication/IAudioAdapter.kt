package com.ahmadsuyadi.myapplication

import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.myapplication.utils.BaseIAudioAdapter

interface IAudioAdapter: BaseIAudioAdapter {
    fun addToFavorite(audio: Audio)
    fun addToPlaylist(audio: Audio)
    fun downloadAudio(audio: Audio)
}