package com.active.transactiontest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TransactionSharedViewModel @Inject constructor(
): ViewModel() {
    private val _addEnable = MutableStateFlow<Boolean>(true)
    val addEnable: StateFlow<Boolean> = _addEnable

    private val _refreshList = MutableStateFlow<Boolean>(false)
    val refreshList: StateFlow<Boolean> = _refreshList

    fun enableAddButton(){
        _addEnable.value = true
    }

    fun desableAddButton(){
        _addEnable.value = false
    }

    fun setRefresh(refresh: Boolean){
        _refreshList.value = refresh
    }
}
