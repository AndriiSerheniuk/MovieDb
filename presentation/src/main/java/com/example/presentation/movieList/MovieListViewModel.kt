package com.example.presentation.movieList

import android.content.Context
import android.net.NetworkInfo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.domain.movieList.entity.MovieItem
import com.example.domain.movieList.repository.MovieRepository
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {

    private var sourceListing = MutableLiveData<Listing<MovieItem>>()
    var moviePagedList = Transformations.map(sourceListing) { it.pagedList }
    var loading = Transformations.switchMap(sourceListing) { it.loading }
    var errorLoading = Transformations.switchMap(sourceListing) { it.errorState }

    var noConnection = MutableLiveData<Boolean>()

    private var compositeDisposable = CompositeDisposable()

    fun startListenNetworkConnection(applicationContext: Context) {
        ReactiveNetwork
            .observeNetworkConnectivity(applicationContext)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ connectivity ->
                if (connectivity.state() == NetworkInfo.State.CONNECTED) {
                    if (sourceListing.value == null)
                        sourceListing.value =
                            MoviesDataSourceFactory.create(movieRepository) //start loading

                    noConnection.value = false
                } else {
                    noConnection.value = true
                }
            }, {
                //ignore error
            }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        sourceListing.value?.clear?.invoke()
        super.onCleared()
    }
}