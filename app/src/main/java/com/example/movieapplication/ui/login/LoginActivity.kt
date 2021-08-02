package com.example.movieapplication.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.movieapplication.MainActivity
import com.example.movieapplication.databinding.ActivityLoginBinding
import com.example.movieapplication.model.Resource
import com.example.movieapplication.ui.main.favorite.FavoriteViewModel
import com.example.movieapplication.viewmodels.ViewModelProvidersFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity: DaggerAppCompatActivity() {
    var binding: ActivityLoginBinding? = null
    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(LoginViewModel::class.java)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        if(sharedPreferences.getString("username", "") != "") {
            goToMain()
        } else {
            binding?.run {
                btnLogin.setOnClickListener {
                    val username = etUsername.text.toString()
                    val password = etPassword.text.toString()
                    viewModel.login(username, password)
                }
            }
            subscribeObserver()
        }
    }

    private fun subscribeObserver() {
        viewModel.loginLiveData.observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> {}
                Resource.Status.SUCCESS -> goToMain()
                Resource.Status.ERROR -> {}
            }
        })
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish();
    }
}