package com.example.movieapplication.ui.main.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.broadcastreceiver.MarkAsFavoriteReceiver
import com.example.movieapplication.model.MarkAsFavoriteRequest
import com.example.movieapplication.model.Movies
import com.example.movieapplication.model.Resource
import com.example.movieapplication.network.movie.MovieApi
import com.example.movieapplication.utils.Constants
import com.example.movieapplication.utils.SchedulerTransformer
import com.example.movieapplication.utils.customSubscribe
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val movieApi: MovieApi
) : ViewModel() {
    private val disposable = CompositeDisposable()

    internal var currentPage = Constants.START_PAGE

    private val _moviesLiveData = MutableLiveData<Resource<Movies>>()
    val moviesLiveData: LiveData<Resource<Movies>> = _moviesLiveData

    private val _markAsFavorite = MutableLiveData<Boolean>()
    val markAsFavorite: LiveData<Boolean> = _markAsFavorite

    fun getFavoriteMovies(page: Int = Constants.START_PAGE) {
        _moviesLiveData.value = Resource.loading(null)
        disposable.add(movieApi.getFavoriteMovies(page = page).compose(SchedulerTransformer()).customSubscribe({
            _moviesLiveData.value = Resource.success(it)
        }, {
            _moviesLiveData.value = it.message?.let { it1 -> Resource.error(it1, null) }
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