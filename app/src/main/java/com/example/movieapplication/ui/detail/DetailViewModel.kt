package com.example.movieapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.model.Movie
import com.example.movieapplication.model.Resource
import com.example.movieapplication.network.movie.MovieApi
import com.example.movieapplication.utils.SchedulerTransformer
import com.example.movieapplication.utils.customSubscribe
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val movieApi: MovieApi
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _movieLiveData = MutableLiveData<Resource<Movie>>()
    val movieLiveData: LiveData<Resource<Movie>> = _movieLiveData

    fun getPopularMovies(movieId: Int) {
        _movieLiveData.value = Resource.loading(null)
        disposable.add(movieApi.getDetailMovie(movieId = movieId).compose(SchedulerTransformer()).customSubscribe({
            _movieLiveData.value = Resource.success(it)
        }, {
            _movieLiveData.value = it.message?.let { it1 -> Resource.error(it1, null) }
        }))
    }

    override fun onCleared() {
        disposable.clear()
    }
}
