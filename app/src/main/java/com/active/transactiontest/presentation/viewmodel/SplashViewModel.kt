package com.active.transactiontest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.active.transactiontest.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val SPLASH_TIMER: Long = 2000L

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: LoginUseCase
): ViewModel() {

    private val _loading = MutableStateFlow<Boolean>(true)
    val loading: StateFlow<Boolean> = _loading

    private var hasSession = false

    init {
        splashTimer()
        verifyLoginSession()
    }
    fun splashTimer() {
        viewModelScope.launch {
            delay(SPLASH_TIMER)
            _loading.value = false
        }
    }

    fun verifyLoginSession() {
        viewModelScope.launch {
            useCase.hasLoginSession().collect{exist ->
                hasSession = exist
            }
        }
    }

    fun hasSession() = hasSession
}