package com.active.transactiontest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TransactionSharedViewModel() : ViewModel() {
    private val _addEnable = MutableStateFlow<Boolean>(true)
    val addEnable: StateFlow<Boolean> = _addEnable

    fun enableAddButton(){
        _addEnable.value = true
    }

    fun desableAddButton(){
        _addEnable.value = false
    }
}
