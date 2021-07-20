package com.example.movieapplication.ui.main.popular

import android.view.View
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentPopularBinding
import com.example.movieapplication.ui.BaseFragment

class PopularFragment: BaseFragment<FragmentPopularBinding>() {

    override fun getContentResource(): Int = R.layout.fragment_popular

    override fun setupViewBinding(view: View) {
        binding = FragmentPopularBinding.bind(view)
    }
}