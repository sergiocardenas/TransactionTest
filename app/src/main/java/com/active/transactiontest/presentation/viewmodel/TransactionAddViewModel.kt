package com.active.transactiontest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.active.transactiontest.domain.usecases.TransactionUseCase
import com.active.transactiontest.presentation.mapper.toModel
import com.active.transactiontest.presentation.state.TransactionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionAddViewModel @Inject constructor(
    private val useCase: TransactionUseCase
): ViewModel() {


    private val _finishAdding = MutableStateFlow(false)
    val finishAdding: StateFlow<Boolean> = _finishAdding

    fun addNewTransaction(value: Int, concept: String, withdraw: Boolean){
        if(value > 0 && concept.isNotEmpty()){
            val newTransaction = TransactionState(value, concept, withdraw)
            addTransaction(newTransaction)
        }
    }

    private fun addTransaction(transaction: TransactionState){
        viewModelScope.launch {
            useCase.addTransaction(transaction.toModel()).collect { success ->
                _finishAdding.value = success
            }
        }
    }
}
