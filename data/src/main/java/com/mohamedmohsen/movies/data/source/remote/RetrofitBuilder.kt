package com.mohamedmohsen.movies.data.source.remote

import com.google.gson.Gson
import com.mohamedmohsen.movies.core.Result
import com.mohamedmohsen.movies.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private lateinit var httpLoggingInterceptor: HttpLoggingInterceptor

    private lateinit var authorizationInterceptor: Interceptor

    private lateinit var okHttpClient: OkHttpClient

    private lateinit var retrofit: Retrofit

    operator fun invoke(): Retrofit {

        if (!RetrofitBuilder::retrofit.isInitialized) {

            synchronized(this) {

                if (!RetrofitBuilder::retrofit.isInitialized) {

                    httpLoggingInterceptor = HttpLoggingInterceptor()

                    when (BuildConfig.DEBUG) {

                        true -> httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                        false -> httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

                    }

                    authorizationInterceptor = Interceptor { chain ->
                        chain.proceed(chain.request().newBuilder().url(chain.request().url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()).build())
                    }

                    okHttpClient = OkHttpClient()
                        .newBuilder()
                        .addInterceptor(authorizationInterceptor)
                        .addInterceptor(httpLoggingInterceptor)
                        .build()

                    retrofit = Retrofit.Builder()
                        .baseUrl(BuildConfig.BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
        }
        return retrofit
    }

    suspend fun <T> safeCall(call: suspend () -> Response<Result<T>>): Result<T> {
        return try {

            val response = call.invoke()

            when (response.isSuccessful) {

                true -> {
                    Result(status = Result.Status.SUCCESS, results = response.body()?.results)
                }

                false -> {
                    Result(status = Result.Status.FAILED, error = Gson().fromJson(response.errorBody()?.string(), Result.Error::class.java))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()

            Result(status = Result.Status.EXCEPTION, error = Result.Error(exception = e))
        }
    }
}