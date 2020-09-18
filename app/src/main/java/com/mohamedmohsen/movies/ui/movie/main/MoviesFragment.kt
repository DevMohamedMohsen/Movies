package com.mohamedmohsen.movies.ui.movie.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkBuilder
import com.mohamedmohsen.movies.R
import com.mohamedmohsen.movies.core.Result
import com.mohamedmohsen.movies.core.movie.Movie
import com.mohamedmohsen.movies.databinding.MoviesFragmentBinding
import com.mohamedmohsen.movies.ui.movie.details.MovieDetailsFragmentArgs
import com.mohamedmohsen.movies.util.Extension
import com.mohamedmohsen.movies.util.PushNotificationBuilder

class MoviesFragment : Fragment(), MoviesAdapter.OnMovieClickListener {

    private val viewModel: MoviesViewModel by lazy {
        ViewModelProvider(this).get(MoviesViewModel::class.java)
    }

    private lateinit var binding: MoviesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MoviesFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.rvMovies.adapter = MoviesAdapter(this)

        subscribeObservers()
        viewModel.getMovies()

        return binding.root
    }

    private fun subscribeObservers() {
        viewModel.movies.observe(viewLifecycleOwner, {
            if (it.status != Result.Status.SUCCESS) {
                Extension.resultError(requireContext(), it.status, it.error)
            }
        })
    }

    override fun onMovieItemClick(movie: Movie) {
        movieDetailsNotification(movie.title, movie.overview)
    }

    private fun movieDetailsNotification(title: String, text: String) {
        val bundle = MovieDetailsFragmentArgs.Builder("$title \n\n $text").build().toBundle()
        val pendingIntent = NavDeepLinkBuilder(requireContext().applicationContext)
            .setGraph(R.navigation.graph)
            .setDestination(R.id.movieDetailsFragment)
            .setArguments(bundle)
            .createPendingIntent()

        PushNotificationBuilder(requireContext().applicationContext, title, text, pendingIntent)
    }
}