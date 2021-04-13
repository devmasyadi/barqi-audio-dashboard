package com.ahmadsuyadi.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.myapplication.databinding.ItemAudioBinding
import com.ahmadsuyadi.myapplication.utils.showPopMenuAudio
import com.ahmadsuyadi.myapplication.utils.showPopMenuFavoriteAudio

class ListFavoriteAudioAdapter(private val context: Context): RecyclerView.Adapter<ListFavoriteAudioAdapter.ViewHolder>() {

    private var listAudio = ArrayList<Audio>()
    private var iAudio: IAudioFavoriteAdapter? = null

    fun setIAudio(iAudio: IAudioFavoriteAdapter) {
        this.iAudio = iAudio
    }

    fun setListAudio(listAudio: List<Audio>) {
        if(this.listAudio.size > 0)
            this.listAudio.clear()
        this.listAudio.addAll(listAudio)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemAudioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Audio) {
            with(binding) {
                tvTitleAudio.text = data.title
                tvArtist.text = data.artist
                imgMore.setOnClickListener { it.showPopMenuFavoriteAudio(data, iAudio) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFavoriteAudioAdapter.ViewHolder {
        return ViewHolder(ItemAudioBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ListFavoriteAudioAdapter.ViewHolder, position: Int) {
        holder.bind(listAudio[position])
    }

    override fun getItemCount() = listAudio.size
}