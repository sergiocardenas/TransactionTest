package com.active.transactiontest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

const val SPLASH_TIMER: Long = 2000L

class SplashViewModel() : ViewModel() {

    private val _loading = MutableStateFlow<Boolean>(true)
    val loading: StateFlow<Boolean> = _loading

    fun splashTimer() {
        viewModelScope.launch {
            delay(SPLASH_TIMER)
            _loading.value = false
        }
    }
}