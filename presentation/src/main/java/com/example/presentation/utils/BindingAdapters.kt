package com.example.presentation.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.movieList.entity.MovieItem
import com.example.presentation.movieList.MovieListAdapter

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleUnless")
fun visibleUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("movies")
fun bindMovieList(recyclerView: RecyclerView, movieList: PagedList<MovieItem>?) {

    movieList ?: return

    if (recyclerView.adapter == null)
        recyclerView.adapter = MovieListAdapter().apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }


    (recyclerView.adapter as MovieListAdapter).submitList(movieList)
}