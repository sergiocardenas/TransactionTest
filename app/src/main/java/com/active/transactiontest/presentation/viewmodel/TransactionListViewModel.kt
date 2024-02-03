package com.active.transactiontest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.active.transactiontest.domain.usecases.TransactionUseCase
import com.active.transactiontest.presentation.mapper.toState
import com.active.transactiontest.presentation.state.TransactionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val useCase: TransactionUseCase
): ViewModel() {

    private val _list = MutableStateFlow<List<TransactionState>>(mutableListOf())
    val list: StateFlow<List<TransactionState>> = _list

    private val _total = MutableStateFlow<Int>(0)
    val total: StateFlow<Int> = _total

    init {
        getTransactionList()
    }

    fun getTransactionList(){
        viewModelScope.launch {
            useCase.getTransactionList().collect(){ transactions ->
                _list.value = transactions.map { transaction -> transaction.toState() }
            }
        }
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
