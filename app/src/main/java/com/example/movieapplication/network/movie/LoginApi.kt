package com.example.movieapplication.network.movie

import com.example.movieapplication.model.GeneralResponse
import com.example.movieapplication.model.LoginRequest
import com.example.movieapplication.model.LoginResponse
import com.example.movieapplication.model.MarkAsFavoriteRequest
import com.example.movieapplication.utils.Constants
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LoginApi {

    @POST("login")
    fun login(
        @Body request: LoginRequest
    ): Single<LoginResponse>
}
