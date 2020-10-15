package com.example.presentation.utils

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified VM : ViewModel> FragmentActivity.provideViewModel(provider: ViewModelProvider.Factory): VM {
    return ViewModelProvider(this, provider).get(VM::class.java)
}