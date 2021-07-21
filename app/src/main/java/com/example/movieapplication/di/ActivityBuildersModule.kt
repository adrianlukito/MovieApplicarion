package com.example.movieapplication.di

import com.example.movieapplication.MainActivity
import com.example.movieapplication.di.detail.DetailFragmentBuildersModule
import com.example.movieapplication.di.detail.DetailModule
import com.example.movieapplication.di.main.MainFragmentBuildersModule
import com.example.movieapplication.di.main.MainModule
import com.example.movieapplication.di.main.MainScope
import com.example.movieapplication.di.main.MainViewModelsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = [
            MainModule::class,
            MainFragmentBuildersModule::class,
            MainViewModelsModule::class,
            DetailModule::class,
            DetailFragmentBuildersModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}
