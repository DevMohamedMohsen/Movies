package com.mohamedmohsen.movies.data.gateway

import com.mohamedmohsen.movies.core.Result
import com.mohamedmohsen.movies.core.movie.Movie
import com.mohamedmohsen.movies.core.movie.MovieGateway
import com.mohamedmohsen.movies.core.movie.Trailer
import com.mohamedmohsen.movies.data.source.remote.RetrofitBuilder
import com.mohamedmohsen.movies.data.source.remote.api.MovieAPIs

class MovieGatewayImplementer(private val movieAPIs: MovieAPIs = RetrofitBuilder().create(MovieAPIs::class.java)) : MovieGateway {

    override suspend fun getNowPlaying(): Result<List<Movie>> = RetrofitBuilder.safeCall { movieAPIs.getNowPlaying() }

    override suspend fun getTrailers(movieID: Int): Result<List<Trailer>> = RetrofitBuilder.safeCall { movieAPIs.getTrailers(movieID) }
}