package com.mohamedmohsen.movies.ui.movie.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieDetailsViewModel(movie: String) : ViewModel() {

    private val _movie = MutableLiveData(movie)
    val movie: LiveData<String> = _movie
}