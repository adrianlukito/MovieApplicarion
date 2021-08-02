package com.example.movieapplication.di.login

import com.example.movieapplication.di.LoginScope
import com.example.movieapplication.di.MainScope
import com.example.movieapplication.network.movie.LoginApi
import com.example.movieapplication.network.movie.MovieApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class LoginModule {

    @LoginScope
    @Provides
    fun provideLoginApi(@Named("provideLoginRetrofit") retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }
}