package com.example.presentation.movieList

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.presentation.R
import com.example.presentation.databinding.ActivityMoviesBinding
import com.example.presentation.utils.provideViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MoviesActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MoviesViewModel
    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel(viewModelFactory)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies)

        binding.apply {
            lifecycleOwner = this@MoviesActivity
            viewModel = this@MoviesActivity.viewModel
        }
    }
}