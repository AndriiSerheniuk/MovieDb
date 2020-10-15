package com.example.presentation.mainActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var loading = MutableLiveData<Boolean>()
}