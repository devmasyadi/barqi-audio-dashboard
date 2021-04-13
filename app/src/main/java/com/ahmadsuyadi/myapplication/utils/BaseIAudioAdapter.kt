package com.ahmadsuyadi.myapplication.utils

import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio

interface BaseIAudioAdapter {
    fun onAddToRecent(audio: Audio)
}