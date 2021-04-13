package com.ahmadsuyadi.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.ahmadsuyadi.barqiaudiodashboard.core.data.Resource
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Playlist
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.PlaylistAndAudios
import com.ahmadsuyadi.barqiaudiodashboard.core.viewmodel.BarqiDashboardViewModel
import com.ahmadsuyadi.myapplication.databinding.ActivityMainBinding
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), AnkoLogger {

    private var binding: ActivityMainBinding? = null
    private val barqiDashboardViewModel: BarqiDashboardViewModel by inject()
    private lateinit var dialogListPlaylist: AlertDialog.Builder
    private lateinit var dialogCreateNewPlaylist: Dialog
    private var audioSelectedToAddPlaylist = Audio()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        dialogListPlaylist = AlertDialog.Builder(this) .apply {
            this.setTitle("Add to Playlist")
            /*this.setView (R.layout.dialog_playlist)
            val listNamePlaylistAdapter = ListNamePlaylistAdapter(this@MainActivity)
            val recyclerView = this.findViewById<RecyclerView>(R.id.recyclerViewListNamePlaylist)
            this.findViewById<Button>(R.id.btnCreateNewPlaylist).setOnClickListener {
                dialogCreateNewPlaylist.show()
            }
            recyclerView.adapter = listNamePlaylistAdapter*/
            barqiDashboardViewModel.getListPlaylist().observe(this@MainActivity, { resources ->
                when(resources) {
                    is Resource.Error -> info("Hallo error: ${resources.message}")
                    is Resource.Loading -> info("Hallo loading")
                    is Resource.Success -> {
//                        resources.data?.let { listNamePlaylistAdapter.setListAudio(it) }
                        resources.data?.let {
                           this.setSingleChoiceItems(it.map { value -> value.playlistName }.toTypedArray(), 0, object: DialogInterface.OnClickListener {
                               override fun onClick(dialog: DialogInterface?, which: Int) {
                                   addToPlaylist(it[which].playlistId!!)
                               }
                           })
                        }
                        info("Hallo success ${resources.data}")
                    }
                }
            })
            this.setNeutralButton("Create New Playlist") { dialog, which ->
                dialogCreateNewPlaylist.show()
            }
        }

        dialogCreateNewPlaylist = Dialog(this).apply {
            setContentView(R.layout.dialog_create_new_playlist)
            val edtNamePlaylist = this.findViewById<EditText>(R.id.edtNamePlaylist)
            this.findViewById<Button>(R.id.btnSubmitNewPlaylist).setOnClickListener {
                barqiDashboardViewModel.addPlaylist(Playlist(playlistName = edtNamePlaylist.text.toString())).observe(this@MainActivity, { resources ->
                    when(resources) {
                        is Resource.Error -> info("Hallo error: ${resources.message}")
                        is Resource.Loading -> info("Hallo loading")
                        is Resource.Success -> {
                           toast("Success add new Playlist")
                            resources.data?.toInt()?.let { it1 -> addToPlaylist(it1) }
                            dialogCreateNewPlaylist.hide()
                        }
                    }
                })
            }
        }

        val listAudioAdapter = ListAudioAdapter(this)
        listAudioAdapter.setIAudio(object : IAudioAdapter {
            override fun addToFavorite(audio: Audio) {
                barqiDashboardViewModel.addAudioToFavorite(audio).observe(this@MainActivity, { resources->
                    when(resources) {
                        is Resource.Error -> info("Hallo error: ${resources.message}")
                        is Resource.Loading -> info("Hallo loading")
                        is Resource.Success -> {
                            info("Hallo success ${resources.data}")
                        }
                    }
                })
            }

            override fun addToPlaylist(audio: Audio) {
                audioSelectedToAddPlaylist = audio
                dialogListPlaylist.show()
            }

            override fun onAddToRecent(audio: Audio) {
                addAudioToRecent(audio)
            }
        })
        binding?.recyclerViewAudio?.adapter = listAudioAdapter
        barqiDashboardViewModel.getAudios().observe(this, { resources ->
            when(resources) {
                is Resource.Error -> info("Hallo error: ${resources.message}")
                is Resource.Loading -> info("Hallo loading")
                is Resource.Success -> {
                    resources.data?.let { listAudioAdapter.setListAudio(it) }
                    info("Hallo success ${resources.data}")
                }
            }
        })

        val listPlaylistAdapter = ListPlaylistAdapter(this)
        listPlaylistAdapter.setIPlaylist(object : IPlaylistAdapter {
            override fun onItemClicked(playlist: PlaylistAndAudios) {
                val intent = Intent(this@MainActivity, DetailPlaylist::class.java)
                intent.putExtra(DetailPlaylist.EXTRA_PLAYLIST_ID, playlist.playlist?.playlistId)
                startActivity(intent)
            }
        })
        binding?.recyclerViewPlaylistAudio?.adapter = listPlaylistAdapter
        barqiDashboardViewModel.getPlaylistAndAudios().observe(this, { resources ->
            when(resources) {
                is Resource.Error -> info("Hallo playlistAndAudio error: ${resources.message}")
                is Resource.Loading -> info("Hallo playlistAndAudio loading")
                is Resource.Success -> {
                    resources.data?.let { listPlaylistAdapter.setListPlaylist(it) }
                    info("Hallo playlistAndAudio success ${resources.data}")
                }
            }
        })


        val listAudioFavoriteAdapter = ListFavoriteAudioAdapter(this)
        listAudioFavoriteAdapter.setIAudio(object : IAudioFavoriteAdapter {
            override fun onDeleteFromFavorite(audio: Audio) {
                barqiDashboardViewModel.removeAudioFromFavorite(audio.id ?: 0).observe(this@MainActivity, { resources ->
                    when(resources) {
                        is Resource.Error -> info("Hallo error: ${resources.message}")
                        is Resource.Loading -> info("Hallo loading")
                        is Resource.Success -> {
                            info("Hallo success ${resources.data}")
                        }
                    }
                })
            }

            override fun onAddToRecent(audio: Audio) {
                addAudioToRecent(audio)
            }
        })
        binding?.recyclerViewFavorite?.adapter = listAudioFavoriteAdapter

        barqiDashboardViewModel.getFavoriteAudios().observe(this, { resources ->
            when(resources) {
                is Resource.Error -> info("Hallo error: ${resources.message}")
                is Resource.Loading -> info("Hallo loading")
                is Resource.Success -> {
                    resources.data?.let { listAudioFavoriteAdapter.setListAudio(it) }
                    info("Hallo success ${resources.data}")
                }
            }
        })

        val listRecentAudioAdapter = ListAudioAdapter(this)
        listRecentAudioAdapter.setIAudio(object : IAudioAdapter {
            override fun addToFavorite(audio: Audio) {
                barqiDashboardViewModel.addAudioToFavorite(audio).observe(this@MainActivity, { resources->
                    when(resources) {
                        is Resource.Error -> info("Hallo error: ${resources.message}")
                        is Resource.Loading -> info("Hallo loading")
                        is Resource.Success -> {
                            info("Hallo success ${resources.data}")
                        }
                    }
                })
            }

            override fun addToPlaylist(audio: Audio) {
                TODO("Not yet implemented")
            }

            override fun onAddToRecent(audio: Audio) {
                addAudioToRecent(audio)
            }
        })
        binding?.recyclerViewRecentAudio?.adapter = listRecentAudioAdapter
        barqiDashboardViewModel.getRecentPlayedAudios().observe(this, { resources ->
            when(resources) {
                is Resource.Error -> info("Hallo error: ${resources.message}")
                is Resource.Loading -> info("Hallo loading")
                is Resource.Success -> {
                    resources.data?.let { listRecentAudioAdapter.setListAudio(it) }
                    info("Hallo success ${resources.data}")
                }
            }
        })

        barqiDashboardViewModel.getDummyTest().observe(this,{ resources ->
            when(resources) {
                is Resource.Error -> info("Hallo getDummyTest error: ${resources.message}")
                is Resource.Loading -> info("Hallo getDummyTest loading")
                is Resource.Success -> {
                    info("Hallo getDummyTest success ${resources.data}")
                }
            }
        })



    }

    private fun addToPlaylist(playlistId: Int) {
        info("Hallo addToPlaylist: $playlistId")
        barqiDashboardViewModel.addAudioToPlaylist(playlistId, audioSelectedToAddPlaylist).observe(this, {resources ->
            when(resources) {
                is Resource.Error -> info("Hallo error: ${resources.message}")
                is Resource.Loading -> info("Hallo loading")
                is Resource.Success -> {
                   toast("Success audio to Playlist")
                }
            }
        })
    }

    private fun addAudioToRecent(audio: Audio) {
        barqiDashboardViewModel.setRecentPlayedAudio(audio)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}