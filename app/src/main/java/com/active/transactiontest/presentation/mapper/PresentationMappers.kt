package com.active.transactiontest.presentation.mapper

import com.active.transactiontest.domain.model.LoginModel
import com.active.transactiontest.domain.model.TransactionModel
import com.active.transactiontest.presentation.state.LoginState
import com.active.transactiontest.presentation.state.TransactionState

fun LoginState.toModel() = LoginModel(
    username = this.username,
    password = this.password
)

fun LoginModel.toState() = LoginState(
    username = this.username,
    password = this.password
)

fun TransactionState.toModel () = TransactionModel(
    value = this.value,
    concept = this.concept,
    withdraw = this.withdraw
)

fun TransactionModel.toState () = TransactionState(
    value = this.value,
    concept = this.concept,
    withdraw = this.withdraw
)
