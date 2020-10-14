package com.example.data.movies.remote

import com.example.data.movies.entity.MoveListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface MovieDbService {

    @GET("movie/popular/")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String = Locale.getDefault().displayLanguage
    ): Single<MoveListResponse>
}