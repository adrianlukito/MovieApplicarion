package com.example.movieapplication.ui.main.toprated

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentTopRatedBinding
import com.example.movieapplication.model.Movie
import com.example.movieapplication.model.Resource
import com.example.movieapplication.ui.BaseFragment
import com.example.movieapplication.ui.main.MainFragmentDirections
import com.example.movieapplication.ui.main.MovieListAdapter
import com.example.movieapplication.ui.main.favorite.FavoriteViewModel
import com.example.movieapplication.ui.main.popular.PopularViewModel
import com.example.movieapplication.utils.Constants
import com.example.movieapplication.utils.InfiniteScrollListener
import com.example.movieapplication.viewmodels.ViewModelProvidersFactory
import javax.inject.Inject

class TopRatedFragment: BaseFragment<FragmentTopRatedBinding>() {

    private lateinit var viewModel: TopRatedViewModel

    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    override fun getContentResource(): Int = R.layout.fragment_top_rated

    private val adapter by lazy {
        MovieListAdapter(
            onItemClicked = { goToDetail(it) },
            onFavoriteClicked = { movieId: Int, isFavorite: Boolean ->
                markAsFavorite(movieId, isFavorite)
            }
        )
    }

    private val scrollListener by lazy {
        InfiniteScrollListener(binding?.rvTopRatedMovies?.layoutManager as GridLayoutManager) {
            viewModel.currentPage = it
            viewModel.getTopRatedMovies(it)
        }
    }

    private fun goToDetail(movieId: Int) {
        val directions = MainFragmentDirections.goToDetail().setMovieId(movieId)
        NavHostFragment.findNavController(this).navigate(directions)
    }

    override fun setupViewBinding(view: View) {
        binding = FragmentTopRatedBinding.bind(view)
        binding?.run {
            rvTopRatedMovies.adapter = adapter
            rvTopRatedMovies.addOnScrollListener(scrollListener)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(TopRatedViewModel::class.java)
        viewModel.getTopRatedMovies()
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
    }

    private fun handleLoading() {
        binding?.run {
            if(viewModel.currentPage == Constants.START_PAGE) {
                loading.isVisible = true
                rvTopRatedMovies.isVisible = false
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
            rvTopRatedMovies.isVisible = true
            loading.isVisible = false
            tvError.isVisible = false
            footerLoading.isVisible = false
        }
    }

    private fun handleError() {
        binding?.run {
            loading.isVisible = false
            rvTopRatedMovies.isVisible = false
            tvError.isVisible = true
            footerLoading.isVisible = false
        }
    }
}
