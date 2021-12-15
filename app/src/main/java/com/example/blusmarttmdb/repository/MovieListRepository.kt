package com.example.blusmarttmdb.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.blusmarttmdb.api.TMDBApi
import com.example.blusmarttmdb.db.PopularMovieDao
import com.example.blusmarttmdb.models.MovieResponse
import com.example.blusmarttmdb.models.PopularMovie
import com.example.blusmarttmdb.utils.LANGUAGE
import com.example.blusmarttmdb.utils.NetworkUtils
import com.example.blusmarttmdb.utils.PER_PAGE_ITEMS
import kotlinx.coroutines.flow.collect

class MovieListRepository(
    private val movielistAPI: TMDBApi,
    private val popularMovieDao: PopularMovieDao
) {
    suspend fun getMovieList() {

        val pagerData = Pager(
            config = PagingConfig(
                pageSize = PER_PAGE_ITEMS,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PopularMovieDataSource(movielistAPI)
            }
        ).flow

        pagerData.collect {
            popularMovieDao.insertPopularMovie(it)
        }

    }

    fun popularMovies(): LiveData<PagingData<PopularMovie>>{
        return popularMovieDao.getPopularMovies()
    }
}