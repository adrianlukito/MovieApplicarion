package com.example.movieapplication.model

import com.google.gson.annotations.SerializedName

data class GeneralResponse(
    @SerializedName("status_code") val statusCode : Int,
    @SerializedName("status_message") val statusMessage : String
)