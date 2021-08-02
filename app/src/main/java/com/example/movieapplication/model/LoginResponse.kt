package com.example.movieapplication.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("username") val username: String
)
