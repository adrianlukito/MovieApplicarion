package com.example.movieapplication.network.movie

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("posts")
    fun getPostFromUser(@Query("userId") id: Int): Flowable<List<String>>
}