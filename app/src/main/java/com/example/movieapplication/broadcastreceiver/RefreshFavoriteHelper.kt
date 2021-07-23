package com.example.movieapplication.broadcastreceiver

import android.content.Context
import android.content.IntentFilter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.movieapplication.ui.main.favorite.FavoriteViewModel
import com.example.movieapplication.utils.Constants

class RefreshFavoriteHelper(
    lifecycle: Lifecycle,
    private val context: Context,
    private val favoriteViewModel: FavoriteViewModel
) : LifecycleObserver {

    init {
        lifecycle.addObserver(this@RefreshFavoriteHelper)
    }

    private var register = false

    private val refreshTaskReceiver = MarkAsFavoriteReceiver {
        favoriteViewModel.getFavoriteMovies()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun register() {
        val filter = IntentFilter()
        filter.addAction(Constants.MARK_FAVORITE_RECEIVED_ACTION)
        filter.priority = 1000
        register = true
        context.registerReceiver(refreshTaskReceiver, filter)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unRegister() {
        if (register) {
            register = false
            context.unregisterReceiver(refreshTaskReceiver)
        }
    }

}
