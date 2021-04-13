package com.ahmadsuyadi.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Playlist
import com.ahmadsuyadi.myapplication.databinding.ItemNamePlaylistBinding

class ListNamePlaylistAdapter(private val context: Context): RecyclerView.Adapter<ListNamePlaylistAdapter.ViewHolder>() {

    private var listAudio = ArrayList<Playlist>()
    private var iAudio: INamePlaylistAdapter? = null

    fun setIAudio(iAudio: INamePlaylistAdapter) {
        this.iAudio = iAudio
    }

    fun setListAudio(listAudio: List<Playlist>) {
        if(this.listAudio.size > 0)
            this.listAudio.clear()
        this.listAudio.addAll(listAudio)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemNamePlaylistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Playlist) {
            with(binding) {
                tvPlaylistName.text = data.playlistName
                root.setOnClickListener { iAudio?.onItemClicked(data) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNamePlaylistAdapter.ViewHolder {
        return ViewHolder(ItemNamePlaylistBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ListNamePlaylistAdapter.ViewHolder, position: Int) {
        holder.bind(listAudio[position])
    }

    override fun getItemCount() = listAudio.size
}