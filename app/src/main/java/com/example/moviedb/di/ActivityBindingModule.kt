package com.example.moviedb.di

import com.example.presentation.mainActivity.MainActivity
import com.example.presentation.mainActivity.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun mainActivity(): MainActivity
}