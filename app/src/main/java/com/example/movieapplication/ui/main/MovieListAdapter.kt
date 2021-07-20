package com.example.movieapplication.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.databinding.ItemMovieBinding

class MovieListAdapter(
    val context: Context,
    private val movies: List<String>,
    private val onItemClicked: (() -> Unit)? = null,
): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.cvMovie.setOnClickListener {
            onItemClicked?.invoke()
            Toast.makeText(context, "CLICKED $position", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)
}
