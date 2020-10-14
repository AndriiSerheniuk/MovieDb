package com.example.domain.movieList.entity

data class MovieItem(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val voteAverage: Double
)