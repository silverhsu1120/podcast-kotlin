package com.example.podcast.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.podcast.model.bean.Content
import com.example.podcast.ui.TrackListAdapter

object BindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImageUrl(view: ImageView, url: String?) {
        Glide.with(view)
            .load(url)
            .centerCrop()
            .into(view)
    }

    @BindingAdapter("trackList")
    @JvmStatic
    fun loadTrackList(view: RecyclerView, contents: List<Content>?) {
        (view.adapter as? TrackListAdapter)?.load(contents ?: emptyList())
    }
}
