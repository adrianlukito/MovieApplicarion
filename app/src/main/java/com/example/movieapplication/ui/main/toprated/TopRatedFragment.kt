package com.example.movieapplication.ui.main.toprated

import android.view.View
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentTopRatedBinding
import com.example.movieapplication.ui.BaseFragment

class TopRatedFragment: BaseFragment<FragmentTopRatedBinding>() {

    override fun getContentResource(): Int = R.layout.fragment_top_rated

    override fun setupViewBinding(view: View) {
        binding = FragmentTopRatedBinding.bind(view)
    }
}
