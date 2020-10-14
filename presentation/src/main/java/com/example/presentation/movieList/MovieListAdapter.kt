package com.example.presentation.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.movieList.entity.MovieItem
import com.example.presentation.databinding.ItemMovieBinding

class MovieListAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    var movies = ArrayList<MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        return ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            MovieViewHolder(this)
        }
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[holder.adapterPosition])
    }

    fun submitList(items: List<MovieItem>) {
        movies.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}

class MovieViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movieItem: MovieItem) {
        binding.title.text = movieItem.title

        val fullPosterPath = "http://image.tmdb.org/t/p/w500/" + movieItem.posterPath
        Glide.with(itemView.context).load(fullPosterPath).into(binding.imagePoster)
    }
}