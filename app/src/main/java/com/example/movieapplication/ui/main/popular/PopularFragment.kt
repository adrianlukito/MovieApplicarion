package com.example.movieapplication.ui.main.popular

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentPopularBinding
import com.example.movieapplication.ui.BaseFragment
import com.example.movieapplication.ui.main.MovieListAdapter
import com.example.movieapplication.viewmodels.ViewModelProvidersFactory
import javax.inject.Inject

class PopularFragment: BaseFragment<FragmentPopularBinding>() {

    private lateinit var viewModel: PopularViewModel

    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    override fun getContentResource(): Int = R.layout.fragment_popular

    private val adapter by lazy {
        MovieListAdapter(requireContext(), listOf("asd", "dsa", "asd", "dsa", "asd", "dsa")) {
            goToDetail()
        }
    }

    private fun goToDetail() {
        NavHostFragment.findNavController(this).navigate(R.id.goToDetail)
    }

    override fun setupViewBinding(view: View) {
        binding = FragmentPopularBinding.bind(view)
        binding?.run {
            rvPopularMovies.adapter = adapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(PopularViewModel::class.java)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.test()
    }
}