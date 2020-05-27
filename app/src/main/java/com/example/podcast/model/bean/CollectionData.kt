package com.example.podcast.model.bean

import com.google.gson.annotations.SerializedName

data class CollectionData(

    @SerializedName("collection")
    val collection: Collection? = null
)
