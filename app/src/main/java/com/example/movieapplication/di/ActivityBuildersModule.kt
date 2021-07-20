package com.example.movieapplication.di

import com.example.movieapplication.MainActivity
import com.example.movieapplication.di.main.MainFragmentBuildersModule
import com.example.movieapplication.di.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            MainModule::class,
            MainFragmentBuildersModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}
