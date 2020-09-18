package com.mohamedmohsen.movies.util

import android.content.Context
import android.widget.Toast
import com.mohamedmohsen.movies.R
import com.mohamedmohsen.movies.core.Result
import java.net.ConnectException
import java.net.UnknownHostException

object Extension {

    private fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun resultError(context: Context, status: Result.Status, error: Result.Error) {

        if (status == Result.Status.FAILED) {
            toast(context, error.status_message)
        }

        if (status == Result.Status.EXCEPTION) {
            when (error.exception) {

                is ConnectException, is UnknownHostException -> {
                    toast(context, context.getString(R.string.error_connection))
                }

                else -> {
                    toast(context, context.getString(R.string.error_exception))
                }
            }
        }
    }
}