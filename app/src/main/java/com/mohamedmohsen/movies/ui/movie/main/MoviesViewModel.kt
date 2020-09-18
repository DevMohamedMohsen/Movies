package com.mohamedmohsen.movies.ui.movie.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamedmohsen.movies.core.Result
import com.mohamedmohsen.movies.core.movie.Movie
import com.mohamedmohsen.movies.core.movie.movieGetNowPlaying
import com.mohamedmohsen.movies.util.ProgressStatus
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val _progressStatus = MutableLiveData<ProgressStatus>()
    val progressStatus: LiveData<ProgressStatus> = _progressStatus

    private val _movies = MutableLiveData<Result<List<Movie>>>()
    val movies: LiveData<Result<List<Movie>>> = _movies


    fun getMovies() {
        viewModelScope.launch {
            _progressStatus.value = ProgressStatus.SHOW
            _movies.value = movieGetNowPlaying()
            _progressStatus.value = ProgressStatus.HIDE
        }
    }
}