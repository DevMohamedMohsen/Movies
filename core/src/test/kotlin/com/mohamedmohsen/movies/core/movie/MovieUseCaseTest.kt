package com.mohamedmohsen.movies.core.movie

import com.mohamedmohsen.movies.core.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MovieUseCaseTest {

    @ExperimentalCoroutinesApi
    @Test
    fun `movieGetNowPlaying() then invoke getNowPlaying() from gateway`() = runBlockingTest {
        var invoked = false

        // Arrange
        val movieGateway = object : MovieGateway {
            override suspend fun getNowPlaying(): Result<List<Movie>> {
                invoked = true
                return Result(status = Result.Status.SUCCESS, results = listOf(Movie()))
            }

            override suspend fun getTrailers(movieID: Int): Result<List<Trailer>> {
                TODO("Not yet implemented")
            }
        }

        // Act
        movieGetNowPlaying(movieGateway)

        // Assert
        assert(invoked)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `movieGetNowPlaying() with successful getNowPlaying() from gateway then return movies`() = runBlockingTest {
        try {
            // Arrange
            val movieGateway = object : MovieGateway {
                override suspend fun getNowPlaying(): Result<List<Movie>> {
                    return Result(status = Result.Status.SUCCESS, results = listOf(Movie()))
                }

                override suspend fun getTrailers(movieID: Int): Result<List<Trailer>> {
                    TODO("Not yet implemented")
                }
            }

            // Act
            val result = movieGetNowPlaying(movieGateway)

            // Assert
            val expected = Result(status = Result.Status.SUCCESS, results = listOf(Movie()))
            assertEquals(expected, result)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `movieGetNowPlaying() with failed getNowPlaying() from gateway then return failed status`() = runBlockingTest {
        // Arrange
        val movieGateway = object : MovieGateway {
            override suspend fun getNowPlaying(): Result<List<Movie>> {
                return Result(status = Result.Status.FAILED)
            }

            override suspend fun getTrailers(movieID: Int): Result<List<Trailer>> {
                TODO("Not yet implemented")
            }
        }

        // Act
        val result = movieGetNowPlaying(movieGateway)

        // Assert
        val expected = Result(status = Result.Status.FAILED, results = null)
        assertEquals(expected, result)
    }

    @Test
    fun `movieSortByName() with list of movies then return list of movies sorted by name`() {
        // Arrange
        val movies = listOf(
            Movie(title = "c"),
            Movie(title = "b"),
            Movie(title = "a")
        )

        // Act
        val result = movieSortByName(movies)

        // Assert
        val expected = listOf(
            Movie(title = "a"),
            Movie(title = "b"),
            Movie(title = "c")
        )
        assertEquals(expected, result)
    }
}