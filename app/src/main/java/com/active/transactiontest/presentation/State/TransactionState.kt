package com.active.transactiontest.presentation.State

data class TransactionState(
    val value : Int = 0,
    val concept : String = "",
    val withdraw: Boolean = false
)
