package com.example.blusmarttmdb

import android.app.Application
import com.example.blusmarttmdb.api.TMDBApi
import com.example.blusmarttmdb.api.MovieListObject
import com.example.blusmarttmdb.repository.MovieListRepository

class APIApplicationActivity : Application() {
    lateinit var movielistRepository: MovieListRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val listAPI = MovieListObject.getInstance().create(TMDBApi::class.java)
        movielistRepository = MovieListRepository(listAPI, applicationContext)
    }
}