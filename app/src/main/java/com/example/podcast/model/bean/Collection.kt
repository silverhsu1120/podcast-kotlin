package com.example.podcast.model.bean

import com.google.gson.annotations.SerializedName

data class Collection(

    @SerializedName("artistId")
    val artistId: Long? = null,

    @SerializedName("artistName")
    val artistName: String? = null,

    @SerializedName("artworkUrl100")
    val artworkUrl100: String? = null,

    @SerializedName("artworkUrl600")
    val artworkUrl600: String? = null,

    @SerializedName("collectionId")
    val collectionId: Long? = null,

    @SerializedName("collectionName")
    val collectionName: String? = null,

    @SerializedName("contentFeed")
    val contentFeed: List<Content>? = null,

    @SerializedName("country")
    val country: String? = null,

    @SerializedName("genreIds")
    val genreIds: String? = null,

    @SerializedName("genres")
    val genres: String? = null,

    @SerializedName("releaseDate")
    val releaseDate: String? = null
)
