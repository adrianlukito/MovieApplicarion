package com.example.movieapplication.ui.main.popular

import android.util.Log
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.movieapplication.model.Resource
import com.example.movieapplication.network.movie.MovieApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val movieApi: MovieApi
) : ViewModel() {

    fun test() {
        LiveDataReactiveStreams.fromPublisher(
            movieApi.getPopularMovies().map {
                Log.d("lolo", "test: $it")
                Resource.success(it)
            }.onErrorReturn {
                Resource.error("something when wrong", null)
            }.subscribeOn(Schedulers.io())
        )
    }

}