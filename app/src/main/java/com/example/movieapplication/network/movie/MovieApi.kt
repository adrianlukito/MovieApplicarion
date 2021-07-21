package com.example.movieapplication.network.movie

import com.example.movieapplication.model.MarkAsFavoriteRequest
import com.example.movieapplication.model.Movie
import com.example.movieapplication.model.Movies
import com.example.movieapplication.utils.Constants
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.http.*

interface MovieApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = Constants.START_PAGE,
    ): Flowable<Movies>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = Constants.START_PAGE,
    ): Flowable<Movies>

    @GET("account/{accountId}/favorite/movies")
    fun getFavoriteMovies(
        @Path("accountId") accountId: Int = Constants.ACCOUNT_ID,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("session_id") sessionId: String = Constants.SESSION_ID,
        @Query("language") language: String = "en",
        @Query("sort_by") sortBy: String = "created_at.asc",
        @Query("page") page: Int = Constants.START_PAGE,
    ): Flowable<Movies>

    @POST("account/{account_id}/favorite")
    fun markAsFavorite(@Body request: MarkAsFavoriteRequest): Completable

    @GET("movie/{movieId}")
    fun getDetailMovie(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = "en",
    ): Flowable<Movie>
}