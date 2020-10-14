package com.example.presentation.movieList

import androidx.lifecycle.ViewModel
import com.example.presentation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MoviesActivityModule {

    @Binds
    @IntoMap
    @com.example.presentation.ViewModelKey(MoviesViewModel::class)
    internal abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel
}