package com.ahmadsuyadi.myapplication

import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.myapplication.utils.BaseIAudioAdapter

interface IAudioFavoriteAdapter: BaseIAudioAdapter {
    fun onDeleteFromFavorite(audio: Audio)
}