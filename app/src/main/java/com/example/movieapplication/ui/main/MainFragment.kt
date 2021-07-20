package com.example.movieapplication.ui.main

import android.view.View
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentMainBinding
import com.example.movieapplication.ui.BaseFragment

class MainFragment: BaseFragment<FragmentMainBinding>() {

    override fun getContentResource(): Int = R.layout.fragment_main

    override fun setupViewBinding(view: View) {
        binding = FragmentMainBinding.bind(view)
    }
}