package com.active.transactiontest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.active.transactiontest.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionSharedViewModel @Inject constructor(
    private val useCase: LoginUseCase
): ViewModel() {

    private val _refreshList = MutableStateFlow<Boolean>(false)
    val refreshList: StateFlow<Boolean> = _refreshList

    private val _logoutSuccess = MutableStateFlow<Boolean>(false)
    val logoutSuccess: StateFlow<Boolean> = _logoutSuccess

    fun setRefresh(refresh: Boolean){
        _refreshList.value = refresh
    }

    fun initLogout(){
        viewModelScope.launch{
            useCase.deleteLoginSession().collect{ result ->
                _logoutSuccess.value = result
            }
        }
    }
}
