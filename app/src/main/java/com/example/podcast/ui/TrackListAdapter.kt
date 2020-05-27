package com.example.podcast.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.podcast.databinding.ItemTrackBinding
import com.example.podcast.model.bean.Content


class TrackListAdapter :
    RecyclerView.Adapter<TrackListAdapter.ViewHolder>() {

    private val items = arrayListOf<Content>()

    var onItemClick: ((Int) -> Unit)? = null

    fun load(contents: List<Content>) {
        items.clear()
        items.addAll(contents)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTrackBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

    inner class ViewHolder(private val binding: ItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val track = items[position]
            with(binding) {
                tvTitle.text = track.title

                root.setOnClickListener {
                    onItemClick?.invoke(position)
                }
            }
        }
    }
}