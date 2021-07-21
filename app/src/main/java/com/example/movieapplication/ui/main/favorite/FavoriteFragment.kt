package com.example.movieapplication.ui.main.favorite

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentFavoriteBinding
import com.example.movieapplication.ui.BaseFragment
import com.example.movieapplication.ui.main.popular.PopularViewModel
import com.example.movieapplication.viewmodels.ViewModelProvidersFactory
import javax.inject.Inject

class FavoriteFragment: BaseFragment<FragmentFavoriteBinding>() {

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    override fun getContentResource(): Int = R.layout.fragment_favorite

    override fun setupViewBinding(view: View) {
        binding = FragmentFavoriteBinding.bind(view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(FavoriteViewModel::class.java)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        // call view model
    }
}