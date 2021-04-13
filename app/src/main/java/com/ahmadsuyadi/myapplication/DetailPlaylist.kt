package com.ahmadsuyadi.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmadsuyadi.barqiaudiodashboard.core.data.Resource
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.barqiaudiodashboard.core.viewmodel.BarqiDashboardViewModel
import com.ahmadsuyadi.myapplication.databinding.ActivityDetailPlaylistBinding
import com.ahmadsuyadi.myapplication.utils.IPlaylistAudioAdapter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.koin.android.ext.android.inject

class DetailPlaylist : AppCompatActivity(), AnkoLogger {

    companion object {
        const val EXTRA_PLAYLIST_ID = "playlistId"
    }

    private var binding: ActivityDetailPlaylistBinding? = null
    private val barqiDashboardViewModel: BarqiDashboardViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPlaylistBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val playlistID = intent.getIntExtra(EXTRA_PLAYLIST_ID, 0)

        val listAudioAdapter = PlaylistAudioAdapter(this)
        listAudioAdapter.setIPlaylist(object : IPlaylistAudioAdapter {
            override fun removeFromPlaylist(audio: Audio) {
                audio.id?.let { barqiDashboardViewModel.deleteAudioPlaylist(playlistID, it).observe(this@DetailPlaylist, { resources ->
                    when(resources) {
                        is Resource.Error -> info("Hallo deleteAudioPlaylist error: ${resources.message}")
                        is Resource.Loading -> info("Hallo deleteAudioPlaylist loading")
                        is Resource.Success -> {
                            info("Hallo deleteAudioPlaylist success: ${resources.data}")
                        }
                    }
                }) }
            }
        })
        binding?.recyclerViewDetailPlaylistAudio?.adapter = listAudioAdapter



        barqiDashboardViewModel.getAudiosByPlaylist(playlistID).observe(this, { resources->
            when(resources) {
                is Resource.Error -> info("Hallo getAudiosByPlaylist error: ${resources.message}")
                is Resource.Loading -> info("Hallo getAudiosByPlaylist loading")
                is Resource.Success -> {
                    info("Hallo getAudiosByPlaylist success: ${resources.data}")
                    resources.data?.let { listAudioAdapter.setListPlaylist(it) }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}