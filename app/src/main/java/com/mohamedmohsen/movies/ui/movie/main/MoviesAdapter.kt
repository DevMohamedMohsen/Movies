package com.mohamedmohsen.movies.ui.movie.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mohamedmohsen.movies.core.movie.Movie
import com.mohamedmohsen.movies.databinding.MovieItemBinding

class MoviesAdapter(private val clickListener: OnMovieClickListener) : ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(MoviesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                return MovieViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }

        fun bind(movie: Movie, clickListener: OnMovieClickListener) {
            binding.movie = movie
            binding.clickListener = clickListener
        }
    }

    class MoviesDiffCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    interface OnMovieClickListener {
        fun onMovieItemClick(movie: Movie)
    }
}