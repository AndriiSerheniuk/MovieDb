package com.example.presentation.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.movieList.entity.MovieItem
import com.example.presentation.movieList.MovieListAdapter


@BindingAdapter("movies")
fun bindMovieList(recyclerView: RecyclerView, movieList: List<MovieItem>) {

    if (recyclerView.adapter == null)
        recyclerView.adapter = MovieListAdapter()

    (recyclerView.adapter as MovieListAdapter).submitList(movieList)
}