package com.example.movieapplication.model

import com.google.gson.annotations.SerializedName

class MarkAsFavoriteRequest {
    @SerializedName("media_type") var mediaType: String = "movie"
    @SerializedName("media_id") var mediaId: Int = 0
    @SerializedName("favorite") var favorite: Boolean = false
}
