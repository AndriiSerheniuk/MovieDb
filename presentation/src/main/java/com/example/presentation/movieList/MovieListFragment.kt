package com.example.presentation.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.presentation.databinding.FragmentMovieListBinding
import com.example.presentation.mainActivity.SharedViewModel
import com.example.presentation.utils.EventObserver
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MovieListViewModel by viewModels { viewModelFactory }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var binding: FragmentMovieListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentMovieListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@MovieListFragment
            viewModel = this@MovieListFragment.viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errorLoading.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(requireContext(), it.message ?: "Error has happened", Toast.LENGTH_SHORT)
                .show()
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            sharedViewModel.loading.value = it
        })

        if (savedInstanceState == null)
            viewModel.startListenNetworkConnection(requireActivity().applicationContext)
    }
}