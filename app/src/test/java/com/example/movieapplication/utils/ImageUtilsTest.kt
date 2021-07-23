package com.example.movieapplication.utils

import org.junit.Assert.*
import org.junit.Test

class ImageUtilsTest {

    @Test
    fun checkUrlImage() {
        val result = ImageUtils.getMovieImage("")
        assertEquals(result, Constants.IMAGE_URL)
    }

    @Test
    fun checkUrlImageWithPath() {
        val path = "/x4biAVdPVCghBlsVIzB6NmbghIz.jpg"
        val result = ImageUtils.getMovieImage(path)
        val expected = Constants.IMAGE_URL + path
        assertEquals(result, expected)
    }
}