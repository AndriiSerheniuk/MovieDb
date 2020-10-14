package com.example.presentation.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import androidx.paging.toLiveData
import com.example.domain.movieList.entity.MovieItem
import com.example.domain.movieList.repository.MovieRepository
import com.example.presentation.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

/**
 * A data source factory which also provides a way to observe the last created data source.
 * This allows us to channel its network request status etc back to the UI
 */
class MoviesDataSourceFactory private constructor(private val movieRepository: MovieRepository) :
    DataSource.Factory<Int, MovieItem>() {

    companion object {
        private val pagingConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Constants.MOVIES_PAGE_SIZE)
            .build()

        /**
         * create data source factory
         */
        fun create(movieRepository: MovieRepository): Listing<MovieItem> {
            val sourceFactory = MoviesDataSourceFactory(movieRepository)

            val livePagedList = sourceFactory.toLiveData(pagingConfig)

            return Listing(
                livePagedList,

                errorState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                    it.errorState
                },

                clear = {
                    sourceFactory.sourceLiveData.value?.clear()
                }
            )
        }
    }

    private val compositeDisposable = CompositeDisposable()

    var sourceLiveData = MutableLiveData<PositionalMovieDataSource>()

    override fun create(): DataSource<Int, MovieItem> {
        return PositionalMovieDataSource(movieRepository, compositeDisposable).also {
            sourceLiveData.postValue(it)
        }
    }
}

/**
 * A data source for loading new items
 */
class PositionalMovieDataSource(private val movieRepository: MovieRepository, private val compositeDisposable: CompositeDisposable) : PositionalDataSource<MovieItem>() {
    private var page = 1

    val errorState = MutableLiveData<Throwable>()

    /**
     * first loading of items when page = 1
     */
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MovieItem>) {
        page = 1
        movieRepository.getPopularMovies(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onResult(it, 0)
            }, {
                errorState.postValue(it)
            }).addTo(compositeDisposable)
    }

    /**
     * load range of items when page > 1
     */
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieItem>) {
        movieRepository.getPopularMovies(++page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onResult(it)
            }, { errorState.postValue(it) })
            .addTo(compositeDisposable)
    }

    /**
     * clear data before observer will be destroyed
     */
    fun clear() {
        page = 1
        compositeDisposable.clear()
    }
}

/**
 * Data class that is necessary for a UI to show a listing and interact w/ the rest of the system
 */
data class Listing<T>(
    // the LiveData of paged lists for the UI to observe
    val pagedList: LiveData<PagedList<T>>,
    // represents the error status to show to the user
    val errorState: LiveData<Throwable>,
    // refreshes the whole data and fetches it from scratch.
    val clear: () -> Unit
)
