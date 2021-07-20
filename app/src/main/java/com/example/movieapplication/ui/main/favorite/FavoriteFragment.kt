package com.example.movieapplication.ui.main.favorite

import android.view.View
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentFavoriteBinding
import com.example.movieapplication.ui.BaseFragment

class FavoriteFragment: BaseFragment<FragmentFavoriteBinding>() {

    override fun getContentResource(): Int = R.layout.fragment_favorite

    override fun setupViewBinding(view: View) {
        binding = FragmentFavoriteBinding.bind(view)
    }
}