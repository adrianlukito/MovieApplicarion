package com.example.movieapplication.network.movie

import com.example.movieapplication.model.GeneralResponse
import com.example.movieapplication.model.MarkAsFavoriteRequest
import com.example.movieapplication.model.Movie
import com.example.movieapplication.model.Movies
import com.example.movieapplication.utils.Constants
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.*

interface MovieApi {

    @GET("3/movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = Constants.START_PAGE,
    ): Single<Movies>

    @GET("3/movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = Constants.START_PAGE,
    ): Single<Movies>

    @GET("3/account/{account_id}/favorite/movies")
    fun getFavoriteMovies(
        @Path("account_id") accountId: Int = Constants.ACCOUNT_ID,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("session_id") sessionId: String = Constants.SESSION_ID,
        @Query("language") language: String = "en",
        @Query("sort_by") sortBy: String = "created_at.asc",
        @Query("page") page: Int = Constants.START_PAGE,
    ): Single<Movies>

    @POST("3/account/{account_id}/favorite")
    fun markAsFavorite(
        @Path("account_id") accountId: Int = Constants.ACCOUNT_ID,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("session_id") sessionId: String = Constants.SESSION_ID,
        @Body request: MarkAsFavoriteRequest
    ): Single<GeneralResponse>

    @GET("3/movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = "en",
    ): Single<Movie>
}