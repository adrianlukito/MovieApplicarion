package com.example.movieapplication.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.movieapplication.R
import com.example.movieapplication.ui.main.favorite.FavoriteFragment
import com.example.movieapplication.ui.main.popular.PopularFragment
import com.example.movieapplication.ui.main.toprated.TopRatedFragment

class MainPagerAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
        PopularFragment(),
        TopRatedFragment(),
        FavoriteFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            0 -> context.getString(R.string.popular)
            1 -> context.getString(R.string.top_rated)
            else -> context.getString(R.string.favorite)
        }
    }
}
