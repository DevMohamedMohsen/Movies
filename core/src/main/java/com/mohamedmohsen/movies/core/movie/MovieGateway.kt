package com.mohamedmohsen.movies.core.movie

import com.mohamedmohsen.movies.core.Result

interface MovieGateway {

    suspend fun getNowPlaying(): Result<List<Movie>>

    suspend fun getTrailers(movieID: Int): Result<List<Trailer>>
}