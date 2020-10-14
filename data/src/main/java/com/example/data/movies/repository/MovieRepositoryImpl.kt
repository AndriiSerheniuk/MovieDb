package com.example.data.movies.repository

import com.example.data.movies.remote.MovieDbService
import com.example.domain.movies.repository.MovieRepository

class MovieRepositoryImpl constructor(private val movieDbService: MovieDbService) : MovieRepository {
}