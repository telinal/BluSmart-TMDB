package com.example.blusmarttmdb.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.blusmarttmdb.models.PopularMovie

@Dao
interface PopularMovieDao {
    @Insert
    fun insertPopularMovie(popularMovie: PagingData<PopularMovie>)

    @Query("SELECT * FROM popularMovie")
    fun getPopularMovies(): LiveData<PagingData<PopularMovie>>
}