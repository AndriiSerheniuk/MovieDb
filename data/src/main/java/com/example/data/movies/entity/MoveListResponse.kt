package com.example.data.movies.entity

import com.example.domain.movieList.entity.MovieItem
import com.google.gson.annotations.SerializedName

data class MoveListResponse(
    val page: Int,
    val results: ArrayList<MovieListItem>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int
) {
    fun toDomain(): List<MovieItem> {
           return results. map { item -> MovieItem(item.id, item.title, item.posterPath, item.voteAverage) }.toList()
    }
}