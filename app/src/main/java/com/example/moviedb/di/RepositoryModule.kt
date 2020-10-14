package com.example.moviedb.di

import com.example.data.movies.remote.MovieDbService
import com.example.data.movies.repository.MovieRepositoryImpl
import com.example.domain.movieList.repository.MovieRepository
import com.example.moviedb.di.qualifiers.ApiKey
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideMovieRepository(movieDbService: MovieDbService, @ApiKey apiKey:String): MovieRepository {
        return  MovieRepositoryImpl(movieDbService, apiKey)
    }
}