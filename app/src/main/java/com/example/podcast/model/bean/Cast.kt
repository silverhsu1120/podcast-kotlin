package com.example.podcast.model.bean

import com.google.gson.annotations.SerializedName

data class Cast(

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("artistName")
    val artistName: String? = null,

    @SerializedName("artworkUrl100")
    val artworkUrl100: String? = null
)
