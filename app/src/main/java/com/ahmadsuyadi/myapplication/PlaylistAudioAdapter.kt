package com.ahmadsuyadi.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.myapplication.databinding.ItemAudioBinding
import com.ahmadsuyadi.myapplication.utils.IPlaylistAudioAdapter

class PlaylistAudioAdapter(private val context: Context): RecyclerView.Adapter<PlaylistAudioAdapter.ViewHolder>() {

    private var listPlaylist = ArrayList<Audio>()
    private var iPlaylist: IPlaylistAudioAdapter? = null

    fun setIPlaylist(iAudio: IPlaylistAudioAdapter) {
        this.iPlaylist = iAudio
    }

    fun setListPlaylist(listAudio: List<Audio>) {
        if(this.listPlaylist.size > 0)
            this.listPlaylist.clear()
        this.listPlaylist.addAll(listAudio)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemAudioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Audio) {
            with(binding) {
                tvTitleAudio.text = data.title
                tvArtist.text = data.artist
                root.setOnClickListener { iPlaylist?.removeFromPlaylist(data) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistAudioAdapter.ViewHolder {
        return ViewHolder(ItemAudioBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: PlaylistAudioAdapter.ViewHolder, position: Int) {
        holder.bind(listPlaylist[position])
    }

    override fun getItemCount() = listPlaylist.size
}