package com.example.movieapplication.di.main

import androidx.lifecycle.ViewModel
import com.example.movieapplication.di.ViewModelKey
import com.example.movieapplication.ui.main.favorite.FavoriteViewModel
import com.example.movieapplication.ui.main.popular.PopularViewModel
import com.example.movieapplication.ui.main.toprated.TopRatedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    abstract fun bindPopularViewModel(viewModel: PopularViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopRatedViewModel::class)
    abstract fun bindTopRatedViewModel(viewModel: TopRatedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel
}
