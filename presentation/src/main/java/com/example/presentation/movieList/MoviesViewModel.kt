package com.example.presentation.movieList

import androidx.lifecycle.ViewModel
import com.example.domain.movieList.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :ViewModel() {

    var compositeDisposable = CompositeDisposable()

    fun getPopularMovies() {
        movieRepository.getPopularMovies(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {
            }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}