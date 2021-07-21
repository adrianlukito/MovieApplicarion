package com.example.movieapplication.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.databinding.ItemMovieBinding
import com.example.movieapplication.model.Movie
import com.example.movieapplication.utils.Constants

class MovieListAdapter(
    val context: Context,
    private val onItemClicked: (() -> Unit)? = null,
): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val _movies = mutableListOf<Movie>()
    val movies = _movies

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(movies[position])
    }

    override fun getItemCount(): Int = _movies.size

    fun setData(data: List<Movie>) {
        _movies.clear()
        _movies.addAll(data)
        notifyDataSetChanged()
    }

    fun addMore(data: List<Movie>) {
        _movies.addAll(data)
        notifyItemInserted(_movies.size)
    }

    inner class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Movie) {
            binding.run {
                cvMovie.setOnClickListener {
                    onItemClicked?.invoke()
                }
                tvTitle.text = item.originalTitle
                Glide.with(context).load("${Constants.IMAGE_URL}${item.posterPath}").into(ivMovie)
            }
        }
    }
}
