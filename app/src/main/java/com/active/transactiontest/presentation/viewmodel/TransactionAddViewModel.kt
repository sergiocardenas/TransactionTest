package com.active.transactiontest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.active.transactiontest.R
import com.active.transactiontest.presentation.State.TransactionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TransactionAddViewModel @Inject constructor(
): ViewModel() {


    private val _newTransaction = MutableStateFlow<TransactionState>(TransactionState())
    val newTransaction: StateFlow<TransactionState> = _newTransaction

    fun addNewTransaction(value: Int, concept: String, withdraw: Boolean): Boolean{
        if(value > 0 && concept.isNotEmpty()){
            _newTransaction.value = TransactionState(value, concept, withdraw)
            return true
        }else{
            return false
        }
    }
}
