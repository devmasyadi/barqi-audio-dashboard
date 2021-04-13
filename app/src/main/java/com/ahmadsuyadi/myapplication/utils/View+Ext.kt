package com.ahmadsuyadi.myapplication.utils

import android.view.View
import android.widget.PopupMenu
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.myapplication.IAudioAdapter
import com.ahmadsuyadi.myapplication.IAudioFavoriteAdapter
import com.ahmadsuyadi.myapplication.R

fun View.showPopMenuAudio(audio: Audio, iAudio: IAudioAdapter?) {
    val popupMenu = PopupMenu(context, this)
    popupMenu.inflate(R.menu.menu_audios)
    popupMenu.setOnMenuItemClickListener { menuItem ->
        when(menuItem.itemId) {
            R.id.actionAddToFavorite -> {
                iAudio?.addToFavorite(audio)
                true
            }
            R.id.actionAddToPlaylist ->  {
                iAudio?.addToPlaylist(audio)
                true
            }
            R.id.actionDownloadAudio -> {
                iAudio?.downloadAudio(audio)
                true
            }
            else -> false
        }
    }
    popupMenu.show()
}

fun View.showPopMenuFavoriteAudio(audio: Audio, iAudio: IAudioFavoriteAdapter?) {
    val popupMenu = PopupMenu(context, this)
    popupMenu.inflate(R.menu.menu_favorite_audios)
    popupMenu.setOnMenuItemClickListener { menuItem ->
        when(menuItem.itemId) {
            R.id.actionDeleteFromFavorite -> {
                iAudio?.onDeleteFromFavorite(audio)
                true
            }else -> false
        }
    }
    popupMenu.show()
}