package com.mohamedmohsen.movies.core.movie

import com.mohamedmohsen.movies.core.CoreDependencies

suspend fun movieGetNowPlaying(movieGateway: MovieGateway = CoreDependencies.MovieGateway) = movieGateway.getNowPlaying()

suspend fun movieGetTrailers(movieID: Int, movieGateway: MovieGateway = CoreDependencies.MovieGateway) = movieGateway.getTrailers(movieID)

fun movieSortByName(movie: List<Movie>) = movie.sortedBy { it.title }