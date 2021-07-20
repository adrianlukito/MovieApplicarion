package com.example.movieapplication.ui.main.popular

import android.view.View
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentPopularBinding
import com.example.movieapplication.ui.BaseFragment
import com.example.movieapplication.ui.main.MovieListAdapter

class PopularFragment: BaseFragment<FragmentPopularBinding>() {

    override fun getContentResource(): Int = R.layout.fragment_popular

    private val adapter by lazy {
        MovieListAdapter(requireContext(), listOf("asd", "dsa", "asd", "dsa", "asd", "dsa"))
    }

    override fun setupViewBinding(view: View) {
        binding = FragmentPopularBinding.bind(view)
        binding?.run {
            rvPopularMovies.adapter = adapter
        }
    }
}