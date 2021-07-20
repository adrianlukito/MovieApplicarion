package com.example.movieapplication.ui.detail

import android.view.View
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentDetailBinding
import com.example.movieapplication.ui.BaseFragment

class DetailFragment: BaseFragment<FragmentDetailBinding>() {

    override fun getContentResource(): Int = R.layout.fragment_detail

    override fun setupViewBinding(view: View) {
        binding = FragmentDetailBinding.bind(view)
    }
}