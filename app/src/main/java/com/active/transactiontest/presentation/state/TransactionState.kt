package com.active.transactiontest.presentation.state

data class TransactionState(
    val value : Int = 0,
    val concept : String = "",
    val withdraw: Boolean = false
)
