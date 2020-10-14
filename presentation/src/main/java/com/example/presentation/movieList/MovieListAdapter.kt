package com.example.presentation.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.movieList.entity.MovieItem
import com.example.presentation.databinding.ItemMovieBinding
import com.example.presentation.utils.Constants.MOVIES_BASE_POSTER_URL

class MovieListAdapter : PagedListAdapter<MovieItem, MovieViewHolder>(MOVIE_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            MovieViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(holder.adapterPosition)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieItem>() {

            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem) =
                oldItem.title == newItem.title && oldItem.voteAverage == newItem.voteAverage
        }
    }
}

class MovieViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movieItem: MovieItem) {
        binding.title.text = movieItem.title

        val fullPosterPath = MOVIES_BASE_POSTER_URL + movieItem.posterPath
        Glide.with(itemView.context).load(fullPosterPath).into(binding.imagePoster)
    }
}