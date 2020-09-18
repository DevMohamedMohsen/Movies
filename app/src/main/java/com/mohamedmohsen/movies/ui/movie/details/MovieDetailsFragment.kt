package com.mohamedmohsen.movies.ui.movie.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mohamedmohsen.movies.databinding.MovieDetailsFragmentBinding

class MovieDetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by lazy {
        val movie = arguments?.getString("movie_dl") ?: MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie
        val factory = MovieDetailsViewModelFactory(movie)
        ViewModelProvider(this, factory).get(MovieDetailsViewModel::class.java)
    }

    private lateinit var binding: MovieDetailsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }
}