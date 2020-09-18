package com.mohamedmohsen.movies.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mohamedmohsen.movies.BuildConfig
import com.mohamedmohsen.movies.core.movie.Movie
import com.mohamedmohsen.movies.ui.movie.main.MoviesAdapter

object BindingAdapter {

    @BindingAdapter("bindMovies")
    @JvmStatic
    fun RecyclerView.bindMovies(movies: List<Movie>?) {
        (adapter as MoviesAdapter).submitList(movies)
    }

    @BindingAdapter("bindImage")
    @JvmStatic
    fun ImageView.bindImage(url: String?) {
        load("${BuildConfig.BASE_Image_URL}$url")
    }
}