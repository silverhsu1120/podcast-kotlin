package com.example.podcast.model.bean

import com.google.gson.annotations.SerializedName

data class Content(

    @SerializedName("contentUrl")
    val contentUrl: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("desc")
    val desc: String? = null,

    @SerializedName("publishedDate")
    val publishedDate: String? = null
)
