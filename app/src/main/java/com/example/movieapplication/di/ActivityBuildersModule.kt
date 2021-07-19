package com.example.movieapplication.di

import com.example.movieapplication.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [

        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}
