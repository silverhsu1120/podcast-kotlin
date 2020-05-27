package com.example.podcast.model.bean

import com.google.gson.annotations.SerializedName

data class GetCastDetailRes(

    @SerializedName("data")
    val data: CollectionData? = null
)
