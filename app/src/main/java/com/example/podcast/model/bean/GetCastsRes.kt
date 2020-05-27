package com.example.podcast.model.bean

import com.google.gson.annotations.SerializedName

data class GetCastsRes(

    @SerializedName("data")
    val data: Podcast? = null
)
