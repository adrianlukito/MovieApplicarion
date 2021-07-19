package com.example.movieapplication.di

import androidx.lifecycle.ViewModelProvider
import com.example.movieapplication.viewmodels.ViewModelProvidersFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProvidersFactory: ViewModelProvidersFactory): ViewModelProvider.Factory
}
