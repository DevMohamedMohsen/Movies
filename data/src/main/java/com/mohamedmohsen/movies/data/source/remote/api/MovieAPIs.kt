package com.mohamedmohsen.movies.data.source.remote.api

import com.mohamedmohsen.movies.core.Result
import com.mohamedmohsen.movies.core.movie.Movie
import com.mohamedmohsen.movies.core.movie.Trailer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieAPIs {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(): Response<Result<List<Movie>>>

    @GET("movie/{MOVIE_ID}/videos")
    suspend fun getTrailers(@Path("MOVIE_ID") movieID: Int): Response<Result<List<Trailer>>>
}