package com.example.movieapplication.ui.main.toprated

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentTopRatedBinding
import com.example.movieapplication.ui.BaseFragment
import com.example.movieapplication.ui.main.favorite.FavoriteViewModel
import com.example.movieapplication.ui.main.popular.PopularViewModel
import com.example.movieapplication.viewmodels.ViewModelProvidersFactory
import javax.inject.Inject

class TopRatedFragment: BaseFragment<FragmentTopRatedBinding>() {

    private lateinit var viewModel: TopRatedViewModel

    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    override fun getContentResource(): Int = R.layout.fragment_top_rated

    override fun setupViewBinding(view: View) {
        binding = FragmentTopRatedBinding.bind(view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(TopRatedViewModel::class.java)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        // call view model
    }
}
