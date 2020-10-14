package com.example.moviedb.di

import android.content.Context
import com.example.moviedb.BuildConfig
import com.example.moviedb.MainApplication
import com.example.moviedb.di.qualifiers.ApiKey
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }

    @Provides
    @ApiKey
    fun provideApiKey():String = BuildConfig.API_KEY
}
