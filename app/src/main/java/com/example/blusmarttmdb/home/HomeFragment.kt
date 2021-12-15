package com.example.blusmarttmdb.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.blusmarttmdb.APIApplicationActivity
import com.example.blusmarttmdb.api.MovieListObject
import com.example.blusmarttmdb.api.TMDBApi
import com.example.blusmarttmdb.databinding.HomeFragmentBinding
import com.example.blusmarttmdb.db.TMDBDatabase
import com.example.blusmarttmdb.repository.MovieListRepository
import com.example.blusmarttmdb.repository.PopularMovieDataSource
import com.example.blusmarttmdb.repository.Response
import com.example.blusmarttmdb.viewmodel.MovieListMainViewModel
import com.example.blusmarttmdb.viewmodel.MovieListViewModelFactory
import kotlinx.coroutines.launch
import java.util.zip.Inflater

class HomeFragment : Fragment() {
    lateinit var binding: HomeFragmentBinding
    lateinit var movieListMainViewModel: MovieListMainViewModel
    lateinit var adapter: PopularMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieListMainViewModel = ViewModelProvider(
            this, MovieListViewModelFactory(
                MovieListRepository(
                    MovieListObject.getInstance().create(TMDBApi::class.java),
                    TMDBDatabase.getDatabase(requireActivity()).popularMovieDao()
                )
            )
        )[MovieListMainViewModel::class.java]

        adapter = PopularMovieAdapter()

        binding.popularMovieRecyclerview.adapter = adapter

        binding.popularMovieRecyclerview.setOnClickListener() {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
        }

        getPopularMovies()

    }

    private fun getPopularMovies() {
        movieListMainViewModel.getPopularMovies().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
    }
}