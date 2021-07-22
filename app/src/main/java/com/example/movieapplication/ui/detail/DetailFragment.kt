package com.example.movieapplication.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentDetailBinding
import com.example.movieapplication.model.Movie
import com.example.movieapplication.model.Resource
import com.example.movieapplication.ui.BaseFragment
import com.example.movieapplication.utils.Constants
import com.example.movieapplication.viewmodels.ViewModelProvidersFactory
import javax.inject.Inject

class DetailFragment: BaseFragment<FragmentDetailBinding>() {

    val movieId = 550

    private lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    override fun getContentResource(): Int = R.layout.fragment_detail

    override fun setupViewBinding(view: View) {
        binding = FragmentDetailBinding.bind(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(DetailViewModel::class.java)
        viewModel.getPopularMovies(movieId)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.LOADING -> handleLoading()
                Resource.Status.SUCCESS -> it.data?.let { handleSuccess(it) }
                Resource.Status.ERROR -> handleError()
            }
        })
    }

    private fun handleLoading() {
        binding?.run {
            loading.isVisible = true
            clContent.isVisible = false
            tvError.isVisible = false
        }
    }

    private fun handleSuccess(item: Movie) {
        binding?.run {
            clContent.isVisible = true
            loading.isVisible = false
            tvError.isVisible = false

            Glide.with(requireContext()).load("${Constants.IMAGE_URL}${item.backdropPath}").into(ivMovie)
            Glide.with(requireContext()).load("${Constants.IMAGE_URL}${item.posterPath}").into(ivThumbnail)
            tvTitle.text = item.originalTitle
            tvRate.text = "${item.voteAverage} / 10"
            tvReleaseDate.text = item.releaseDate
            tvOverview.text = item.overview
        }
    }

    private fun handleError() {
        binding?.run {
            loading.isVisible = false
            clContent.isVisible = false
            tvError.isVisible = true
        }
    }
}