package com.example.podcast.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.podcast.databinding.ItemCastBinding
import com.example.podcast.model.bean.Cast

class CastListAdapter(var items: List<Cast>) :
    RecyclerView.Adapter<CastListAdapter.ViewHolder>() {

    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCastBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

    inner class ViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val cast = items[position]
            with(binding) {
                tvTitle.text = cast.artistName
                tvSubtitle.text = cast.name
                Glide.with(ivPhoto)
                    .load(cast.artworkUrl100)
                    .centerCrop()
                    .into(ivPhoto)

                root.setOnClickListener {
                    onItemClick?.invoke(position)
                }
            }
        }
    }
}