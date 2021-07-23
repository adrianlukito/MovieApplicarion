package com.example.movieapplication.utils

object ImageUtils {

    fun getMovieImage(path: String): String {
        val url = Constants.IMAGE_URL
        return url + path
    }
}