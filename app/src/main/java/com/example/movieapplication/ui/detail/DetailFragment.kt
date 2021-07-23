package com.example.movieapplication.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
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
        val movieId = arguments?.let { DetailFragmentArgs.fromBundle(it).movieId }
        movieId?.let {
            viewModel.getPopularMovies(it)
        }
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
        viewModel.markAsFavorite.observe(viewLifecycleOwner, Observer {
            binding?.run {
                if(it) {
                    ivFavorite.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red))
                } else {
                    ivFavorite.setColorFilter(ContextCompat.getColor(requireContext(), R.color.teal_200))
                }
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
            tvViews.text = "${item.voteCount} voters"
            tvReleaseDate.text = item.releaseDate
            tvOverview.text = item.overview

            ivFavorite.setOnClickListener {
                item.isFavorite = !item.isFavorite
                viewModel.markAsFavorite(item.id, item.isFavorite)
            }
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