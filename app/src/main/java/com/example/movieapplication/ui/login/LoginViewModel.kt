package com.example.movieapplication.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.BaseApplication
import com.example.movieapplication.broadcastreceiver.MarkAsFavoriteReceiver
import com.example.movieapplication.model.LoginRequest
import com.example.movieapplication.model.LoginResponse
import com.example.movieapplication.model.MarkAsFavoriteRequest
import com.example.movieapplication.model.Resource
import com.example.movieapplication.network.movie.LoginApi
import com.example.movieapplication.utils.SchedulerTransformer
import com.example.movieapplication.utils.customSubscribe
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginApi: LoginApi,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    private val disposable = CompositeDisposable()

    private val _loginMutableLiveData = MutableLiveData<Resource<LoginResponse>>()
    val loginLiveData: LiveData<Resource<LoginResponse>> = _loginMutableLiveData

    fun login(username: String, password: String) {
        val request = LoginRequest(username, password)
        _loginMutableLiveData.value = Resource.loading(null)
        disposable.add(loginApi.login(request = request).compose(SchedulerTransformer()).customSubscribe({
            _loginMutableLiveData.value = Resource.success(it)
            sharedPreferences.edit().putString("username" , it.username).apply()
        }, {
            _loginMutableLiveData.value = it.message?.let { it1 -> Resource.error(it1, null) }
        }))
    }
}
