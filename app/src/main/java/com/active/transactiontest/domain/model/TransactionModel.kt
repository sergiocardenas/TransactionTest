package com.active.transactiontest.domain.model

data class TransactionModel(
    val value : Int = 0,
    val concept : String = "",
    val withdraw: Boolean = false
)
