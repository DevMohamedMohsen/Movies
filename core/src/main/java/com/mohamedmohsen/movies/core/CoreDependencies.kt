package com.mohamedmohsen.movies.core

import com.mohamedmohsen.movies.core.movie.MovieGateway

object CoreDependencies {

    internal lateinit var MovieGateway: MovieGateway private set

    fun injectMovieGateway(movieGateway: MovieGateway) {
        MovieGateway = movieGateway
    }
}