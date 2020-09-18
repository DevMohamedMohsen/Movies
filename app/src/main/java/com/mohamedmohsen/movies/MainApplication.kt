package com.mohamedmohsen.movies

import android.app.Application
import com.mohamedmohsen.movies.core.CoreDependencies
import com.mohamedmohsen.movies.data.gateway.MovieGatewayImplementer

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        injectCoreModuleDependencies()
    }

    private fun injectCoreModuleDependencies() {
        with(CoreDependencies) {
            injectMovieGateway(MovieGatewayImplementer())
        }
    }
}