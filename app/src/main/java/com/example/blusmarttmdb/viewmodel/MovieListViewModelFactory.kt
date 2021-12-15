package com.example.blusmarttmdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.blusmarttmdb.repository.MovieListRepository

class MovieListViewModelFactory(private val repository: MovieListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieListMainViewModel(repository) as T
    }

}