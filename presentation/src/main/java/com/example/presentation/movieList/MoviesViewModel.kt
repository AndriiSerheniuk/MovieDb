package com.example.presentation.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.domain.movieList.repository.MovieRepository
import javax.inject.Inject

class MoviesViewModel @Inject constructor(movieRepository: MovieRepository) : ViewModel() {

    private var sourceListing = MutableLiveData(MoviesDataSourceFactory.create(movieRepository))
    var moviePagedList = Transformations.map(sourceListing) { it.pagedList }

    override fun onCleared() {
        sourceListing.value?.clear?.invoke()
        super.onCleared()
    }
}