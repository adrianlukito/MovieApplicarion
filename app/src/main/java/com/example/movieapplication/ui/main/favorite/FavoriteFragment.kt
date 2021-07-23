package com.example.movieapplication.ui.main.favorite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.broadcastreceiver.RefreshFavoriteHelper
import com.example.movieapplication.databinding.FragmentFavoriteBinding
import com.example.movieapplication.model.Movie
import com.example.movieapplication.model.Resource
import com.example.movieapplication.ui.BaseFragment
import com.example.movieapplication.ui.main.MainFragmentDirections
import com.example.movieapplication.ui.main.MovieListAdapter
import com.example.movieapplication.utils.Constants
import com.example.movieapplication.utils.InfiniteScrollListener
import com.example.movieapplication.viewmodels.ViewModelProvidersFactory
import javax.inject.Inject

class FavoriteFragment: BaseFragment<FragmentFavoriteBinding>() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var refreshFavoriteHelper: RefreshFavoriteHelper

    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    override fun getContentResource(): Int = R.layout.fragment_favorite

    private val adapter by lazy {
        MovieListAdapter(
            requireContext(),
            onItemClicked = { goToDetail(it) },
            onFavoriteClicked = { movieId: Int, isFavorite: Boolean ->
                markAsFavorite(movieId, isFavorite)
            }
        )
    }

    private val scrollListener by lazy {
        InfiniteScrollListener(binding?.rvFavoriteMovies?.layoutManager as GridLayoutManager) {
            viewModel.currentPage = it
            viewModel.getFavoriteMovies(it)
        }
    }

    private fun goToDetail(movieId: Int) {
        val directions = MainFragmentDirections.goToDetail().setMovieId(movieId)
        NavHostFragment.findNavController(this).navigate(directions)
    }

    override fun setupViewBinding(view: View) {
        binding = FragmentFavoriteBinding.bind(view)
        binding?.run {
            rvFavoriteMovies.adapter = adapter
            rvFavoriteMovies.addOnScrollListener(scrollListener)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(FavoriteViewModel::class.java)
        refreshFavoriteHelper = RefreshFavoriteHelper(lifecycle, requireContext(), viewModel)
        viewModel.getFavoriteMovies()
        subscribeObserver()
    }

    private fun markAsFavorite(movieId: Int, isFavorite: Boolean) {
        viewModel.markAsFavorite(movieId, isFavorite)
    }

    private fun subscribeObserver() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.LOADING -> handleLoading()
                Resource.Status.SUCCESS -> it.data?.let { handleSuccess(it.results) }
                Resource.Status.ERROR -> handleError()
            }
        })
        viewModel.markAsFavorite.observe(viewLifecycleOwner, Observer {
            viewModel.getFavoriteMovies()
        })
    }

    private fun handleLoading() {
        binding?.run {
            if(viewModel.currentPage == Constants.START_PAGE) {
                loading.isVisible = true
                rvFavoriteMovies.isVisible = false
                tvError.isVisible = false
            } else {
                footerLoading.isVisible = true
            }
        }
    }

    private fun handleSuccess(item: List<Movie>) {
        binding?.run {
            item.map { it.isFavorite = true }
            if(viewModel.currentPage == Constants.START_PAGE) {
                adapter.setData(item)
            } else {
                adapter.addMore(item)
            }
            rvFavoriteMovies.isVisible = adapter.movies.isNotEmpty()
            tvEmpty.isVisible = adapter.movies.isEmpty()
            loading.isVisible = false
            tvError.isVisible = false
            footerLoading.isVisible = false
        }
    }

    private fun handleError() {
        binding?.run {
            loading.isVisible = false
            rvFavoriteMovies.isVisible = false
            tvError.isVisible = true
            footerLoading.isVisible = false
        }
    }
}