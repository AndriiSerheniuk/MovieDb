package com.example.domain.movieList.repository

import com.example.domain.movieList.entity.MovieItem
import io.reactivex.Single

interface MovieRepository {
    fun getPopularMovies(page: Int): Single<List<MovieItem>>
}