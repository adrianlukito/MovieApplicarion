package com.example.movieapplication.ui.main.favorite

import androidx.lifecycle.ViewModel
import com.example.movieapplication.network.movie.MovieApi
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val movieApi: MovieApi
) : ViewModel() {

}