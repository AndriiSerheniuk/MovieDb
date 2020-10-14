package com.example.presentation.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.movieList.entity.MovieItem
import com.example.domain.movieList.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val movieItems = MutableLiveData<List<MovieItem>>(listOf())

    fun getPopularMovies() {
        movieRepository.getPopularMovies(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                movieItems.value = it
            }, {
            }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}