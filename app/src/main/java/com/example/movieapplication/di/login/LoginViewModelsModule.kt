package com.example.movieapplication.di.login

import androidx.lifecycle.ViewModel
import com.example.movieapplication.di.ViewModelKey
import com.example.movieapplication.ui.login.LoginViewModel
import com.example.movieapplication.ui.main.popular.PopularViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindPopularViewModel(viewModel: LoginViewModel): ViewModel
}
