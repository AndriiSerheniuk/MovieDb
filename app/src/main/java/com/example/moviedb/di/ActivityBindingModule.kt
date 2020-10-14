package com.example.moviedb.di

import com.example.moviedb.ui.movieList.MoviesActivity
import com.example.moviedb.ui.movieList.MoviesActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [MoviesActivityModule::class])
    internal abstract fun mainActivity(): MoviesActivity
}