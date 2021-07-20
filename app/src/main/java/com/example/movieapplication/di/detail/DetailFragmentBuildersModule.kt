package com.example.movieapplication.di.detail

import com.example.movieapplication.ui.detail.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment
}