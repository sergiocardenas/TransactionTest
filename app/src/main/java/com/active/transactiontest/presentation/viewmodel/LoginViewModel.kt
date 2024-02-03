package com.active.transactiontest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.active.transactiontest.domain.usecases.LoginUseCase
import com.active.transactiontest.presentation.mapper.toModel
import com.active.transactiontest.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase
): ViewModel() {

    private val _hasSession = MutableStateFlow<Boolean>(false)
    val hasSession: StateFlow<Boolean> = _hasSession
    private var _savingLogin = false

    fun saveLogin(username: String, password: String){
        if(username.isNotEmpty() && password.isNotEmpty() && !_savingLogin){
            val session = LoginState(username, password)
            saveLoginSession(session)
            _savingLogin = true
        }
    }

    fun saveLoginSession(login: LoginState){
        viewModelScope.launch {
            useCase.addLoginSession(login.toModel()).collect{ result ->
                _hasSession.value = result
            }
        }
    }
}
