package com.example.movieapplication.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfiniteScrollListener(
    private val layoutManager: GridLayoutManager,
    private var startPage: Int = Constants.START_PAGE,
    private var visibleThreshold: Int = 2,
    private val onLoad: (page: Int) -> Unit
): RecyclerView.OnScrollListener() {

    private var previousTotalItemCount = 0
    private var loading = true
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var currentPage = 0

    init {
        currentPage = startPage
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if(dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if(loading && totalItemCount > previousTotalItemCount) {
                loading = false
                previousTotalItemCount = totalItemCount
                currentPage++
            }

            if(!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                loading = true
                onLoad.invoke(currentPage)
            }
        }
    }

    fun resetState() {
        currentPage = startPage
        previousTotalItemCount = 0
        loading = true
    }
}
