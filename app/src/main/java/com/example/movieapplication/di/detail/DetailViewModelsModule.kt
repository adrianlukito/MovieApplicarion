package com.example.movieapplication.di.detail

import androidx.lifecycle.ViewModel
import com.example.movieapplication.di.ViewModelKey
import com.example.movieapplication.ui.detail.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel
}
