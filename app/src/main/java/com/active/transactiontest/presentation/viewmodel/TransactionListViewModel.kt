package com.active.transactiontest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.active.transactiontest.presentation.State.TransactionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TransactionListViewModel() : ViewModel() {

    private val _list = MutableStateFlow<MutableList<TransactionState>>(mutableListOf())
    val list: StateFlow<List<TransactionState>> = _list

    private val _total = MutableStateFlow<Int>(0)
    val total: StateFlow<Int> = _total

    init {
    }

    fun calculateTotal(){
        var totalAmount = 0
        for (item in _list.value){
            var value = item.value
            if(item.withdraw){
                value *= -1
            }
            totalAmount += value
        }
        _total.value = totalAmount
    }
}
