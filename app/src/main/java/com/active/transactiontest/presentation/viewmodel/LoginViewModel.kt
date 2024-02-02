package com.active.transactiontest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.active.transactiontest.presentation.State.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel() : ViewModel() {

    private val _session = MutableStateFlow<LoginState>(LoginState())
    private val _hasSession = MutableStateFlow<Boolean>(false)
    val hasSession: StateFlow<Boolean> = _hasSession

    fun saveLogin(username: String, password: String){
        if(username.isNotEmpty() && password.isNotEmpty()){
            _session.value = LoginState(username, password)
            _hasSession.value = true
        }
    }
}
