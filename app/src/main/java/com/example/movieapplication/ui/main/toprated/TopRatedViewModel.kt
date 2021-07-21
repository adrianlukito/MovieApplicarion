package com.example.movieapplication.ui.main.toprated

import androidx.lifecycle.ViewModel
import com.example.movieapplication.network.movie.MovieApi
import javax.inject.Inject

class TopRatedViewModel @Inject constructor(
    private val movieApi: MovieApi
) : ViewModel() {

}