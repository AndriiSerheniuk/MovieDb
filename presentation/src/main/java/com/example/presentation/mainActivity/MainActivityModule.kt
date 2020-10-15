package com.example.presentation.mainActivity

import androidx.lifecycle.ViewModel
import com.example.presentation.ViewModelKey
import com.example.presentation.movieList.MovieListFragment
import com.example.presentation.movieList.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {


    @ContributesAndroidInjector
    abstract fun provideMovieListFragment():MovieListFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun bindMoviesViewModel(viewModel: MovieListViewModel): ViewModel
}