package com.example.moviedb.di

import com.example.data.movies.remote.MovieDbService
import com.example.data.movies.repository.MovieRepositoryImpl
import com.example.domain.movies.repository.MovieRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideMovieRepository(movieDbService: MovieDbService): MovieRepository {
        return MovieRepositoryImpl(movieDbService)
    }
}