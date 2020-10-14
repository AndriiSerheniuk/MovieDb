package com.example.data.movies.repository

import com.example.data.movies.remote.MovieDbService
import com.example.domain.movieList.entity.MovieItem
import com.example.domain.movieList.repository.MovieRepository
import io.reactivex.Single

class MovieRepositoryImpl constructor(private val movieDbService: MovieDbService, private val apiKey:String) : MovieRepository {

    override fun getPopularMovies(page: Int): Single<List<MovieItem>> {

        return movieDbService.getPopularMovies(apiKey, page)
            .map { it.toDomain() }
    }
}