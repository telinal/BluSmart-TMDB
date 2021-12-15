package com.example.blusmarttmdb.models

import java.io.Serializable

data class MovieResponse(
    val page: Int,
    val popularMovies: List<PopularMovie>,
    val total_pages: Int,
    val total_results: Int
): Serializable