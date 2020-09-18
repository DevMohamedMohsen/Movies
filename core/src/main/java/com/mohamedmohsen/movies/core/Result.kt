package com.mohamedmohsen.movies.core

data class Result<T>(
    val status: Status,
    val results: T? = null,
    val error: Error = Error()
) {
    enum class Status { SUCCESS, FAILED, EXCEPTION }

    data class Error(
        val status_message: String = "",
        val exception: Exception? = null
    )
}