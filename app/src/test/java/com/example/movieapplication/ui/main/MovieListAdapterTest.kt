package com.example.movieapplication.ui.main

import com.example.movieapplication.R
import org.junit.Assert.*
import org.junit.Test

class MovieListAdapterTest {

    @Test
    fun testFavoriteColor() {
        val movieListAdapter = MovieListAdapter()
        val result = movieListAdapter.toggleFavoriteColor(true)
        assertEquals(result, R.color.red)
    }

    @Test
    fun testUnfavoriteColor() {
        val movieListAdapter = MovieListAdapter()
        val result = movieListAdapter.toggleFavoriteColor(false)
        assertEquals(result, R.color.teal_200)
    }
}