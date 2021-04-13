package com.ahmadsuyadi.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.PlaylistAndAudios
import com.ahmadsuyadi.myapplication.databinding.ItemPlaylistBinding

class ListPlaylistAdapter(private val context: Context): RecyclerView.Adapter<ListPlaylistAdapter.ViewHolder>() {

    private var listPlaylist = ArrayList<PlaylistAndAudios>()
    private var iPlaylist: IPlaylistAdapter? = null

    fun setIPlaylist(iAudio: IPlaylistAdapter) {
        this.iPlaylist = iAudio
    }

    fun setListPlaylist(listAudio: List<PlaylistAndAudios>) {
        if(this.listPlaylist.size > 0)
            this.listPlaylist.clear()
        this.listPlaylist.addAll(listAudio)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PlaylistAndAudios) {
            with(binding) {
                tvPlaylistTitle.text = data.playlist?.playlistName
                tvPlaylistCountSong.text = data.audios?.size.toString()
                root.setOnClickListener { iPlaylist?.onItemClicked(data) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPlaylistAdapter.ViewHolder {
        return ViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ListPlaylistAdapter.ViewHolder, position: Int) {
        holder.bind(listPlaylist[position])
    }

    override fun getItemCount() = listPlaylist.size
}