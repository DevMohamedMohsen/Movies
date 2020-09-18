package com.mohamedmohsen.movies.core.movie

data class Movie(
    val backdrop_path: String = "",
    val id: Int = 0,
    val overview: String = "",
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val vote_average: Double = 0.0
)