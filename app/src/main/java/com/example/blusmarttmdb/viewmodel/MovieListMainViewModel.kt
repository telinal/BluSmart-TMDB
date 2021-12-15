package com.example.blusmarttmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.blusmarttmdb.models.MovieResponse
import com.example.blusmarttmdb.models.PopularMovie
import com.example.blusmarttmdb.repository.MovieListRepository
import com.example.blusmarttmdb.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MovieListMainViewModel(val repository: MovieListRepository) : ViewModel() {


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovieList()
        }
    }

    fun getPopularMovies(): LiveData<PagingData<PopularMovie>> {
        return repository.popularMovies()
    }


}