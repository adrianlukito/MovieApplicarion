package com.example.movieapplication.ui.main.popular

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentPopularBinding
import com.example.movieapplication.model.Movie
import com.example.movieapplication.model.Resource
import com.example.movieapplication.ui.BaseFragment
import com.example.movieapplication.ui.main.MovieListAdapter
import com.example.movieapplication.utils.Constants
import com.example.movieapplication.utils.InfiniteScrollListener
import com.example.movieapplication.viewmodels.ViewModelProvidersFactory
import javax.inject.Inject

class PopularFragment: BaseFragment<FragmentPopularBinding>() {

    private lateinit var viewModel: PopularViewModel

    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    override fun getContentResource(): Int = R.layout.fragment_popular

    private val adapter by lazy {
        MovieListAdapter(requireContext()) {
            goToDetail()
        }
    }

    private val scrollListener by lazy {
        InfiniteScrollListener(binding?.rvPopularMovies?.layoutManager as GridLayoutManager) {
            viewModel.currentPage = it
            viewModel.getPopularMovies(it)
        }
    }

    private fun goToDetail() {
        NavHostFragment.findNavController(this).navigate(R.id.goToDetail)
    }

    override fun setupViewBinding(view: View) {
        binding = FragmentPopularBinding.bind(view)
        binding?.run {
            rvPopularMovies.adapter = adapter
            rvPopularMovies.addOnScrollListener(scrollListener)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(PopularViewModel::class.java)
        viewModel.getPopularMovies()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.LOADING -> handleLoading()
                Resource.Status.SUCCESS -> it.data?.let { handleSuccess(it.results) }
                Resource.Status.ERROR -> handleError()
            }
        })
    }

    private fun handleLoading() {
        binding?.run {
            if(viewModel.currentPage == Constants.START_PAGE) {
                loading.isVisible = true
                rvPopularMovies.isVisible = false
                tvError.isVisible = false
            } else {
                footerLoading.isVisible = true
            }
        }
    }

    private fun handleSuccess(item: List<Movie>) {
        binding?.run {
            if(viewModel.currentPage == Constants.START_PAGE) {
                adapter.setData(item)
            } else {
                adapter.addMore(item)
            }
            rvPopularMovies.isVisible = true
            loading.isVisible = false
            tvError.isVisible = false
            footerLoading.isVisible = false
        }
    }

    private fun handleError() {
        binding?.run {
            loading.isVisible = false
            rvPopularMovies.isVisible = false
            tvError.isVisible = true
            footerLoading.isVisible = false
        }
    }
}