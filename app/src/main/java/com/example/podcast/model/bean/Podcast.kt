package com.example.podcast.model.bean

import com.google.gson.annotations.SerializedName

data class Podcast(

    @SerializedName("podcast")
    val podcast: List<Cast>? = null
)
