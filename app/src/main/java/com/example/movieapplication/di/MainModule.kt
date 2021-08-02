package com.example.movieapplication.di

import com.example.movieapplication.network.movie.MovieApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMovieApi(@Named("provideRetrofit") retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}
