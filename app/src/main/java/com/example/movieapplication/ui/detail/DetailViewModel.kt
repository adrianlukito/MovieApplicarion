package com.example.movieapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.broadcastreceiver.MarkAsFavoriteReceiver
import com.example.movieapplication.model.MarkAsFavoriteRequest
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

    private val _markAsFavorite = MutableLiveData<Boolean>()
    val markAsFavorite: LiveData<Boolean> = _markAsFavorite

    fun getPopularMovies(movieId: Int) {
        _movieLiveData.value = Resource.loading(null)
        disposable.add(movieApi.getDetailMovie(movieId = movieId).compose(SchedulerTransformer()).customSubscribe({
            _movieLiveData.value = Resource.success(it)
        }, {
            _movieLiveData.value = it.message?.let { it1 -> Resource.error(it1, null) }
        }))
    }

    fun markAsFavorite(movieId: Int, isFavorite: Boolean) {
        val request = MarkAsFavoriteRequest(
            mediaId = movieId,
            favorite = isFavorite
        )
        disposable.add(movieApi.markAsFavorite(request = request).compose(SchedulerTransformer()).customSubscribe({
            MarkAsFavoriteReceiver.sendBroadcast()
            _markAsFavorite.value = isFavorite
        }, {
            _markAsFavorite.value = !isFavorite
        }))
    }

    override fun onCleared() {
        disposable.clear()
    }
}
