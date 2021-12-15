package com.example.blusmarttmdb.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popularMovie")
data class PopularMovie(
    @PrimaryKey
    val id: Int,
    val original_language: String,
    val original_title: String,
    val popularity: Double,
    val poster_path: String,
    val title: String,
    val vote_average: Double
)