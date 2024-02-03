package com.active.transactiontest.domain.mapper

import com.active.transactiontest.data.entity.LoginEntity
import com.active.transactiontest.data.entity.TransactionEntity
import com.active.transactiontest.domain.model.LoginModel
import com.active.transactiontest.domain.model.TransactionModel

fun LoginModel.toEntity() = LoginEntity(
    username = this.username,
    password = this.password
)

fun TransactionEntity.toModel () = TransactionModel(
    value = this.value,
    concept = this.concept,
    withdraw = this.withdraw
)

fun TransactionModel.toEntity () = TransactionEntity(
    value = this.value,
    concept = this.concept,
    withdraw = this.withdraw
)
