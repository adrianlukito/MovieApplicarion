package com.example.movieapplication.di.main

import com.example.movieapplication.ui.main.MainFragment
import com.example.movieapplication.ui.main.favorite.FavoriteFragment
import com.example.movieapplication.ui.main.popular.PopularFragment
import com.example.movieapplication.ui.main.toprated.TopRatedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributePopularFragment(): PopularFragment

    @ContributesAndroidInjector
    abstract fun contributeTopRatedFragment(): TopRatedFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): FavoriteFragment
}
