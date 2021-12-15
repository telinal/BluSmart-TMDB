package com.example.blusmarttmdb.api

import com.example.blusmarttmdb.models.MovieResponse
import com.example.blusmarttmdb.repository.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TMDBApi {

    @GET("movie/popular")
    suspend fun getMovieList(
        @Header("api_key") apiKey: String = "",
        @Query("language") language : String,
        @Query("page") page: Int
    ): MovieResponse

}