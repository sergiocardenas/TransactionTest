package com.active.transactiontest.domain.repository

import com.active.transactiontest.data.datasource.LocalDataSource
import com.active.transactiontest.domain.mapper.toEntity
import com.active.transactiontest.domain.mapper.toModel
import com.active.transactiontest.domain.model.LoginModel
import com.active.transactiontest.domain.model.TransactionModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val local : LocalDataSource,
){
    suspend fun getTransactionList(): Flow<List<TransactionModel>> =
        local.getTransactionList().map { list ->
            list.map { entity -> entity.toModel() }
        }

    suspend fun addTransaction(transaction: TransactionModel): Flow<Boolean> =
        local.addTransaction(transaction.toEntity())

    suspend fun hasLoginSession(): Flow<Boolean> =
        local.hasLoginSession()

    suspend fun addLoginSession(login: LoginModel): Flow<Boolean> =
        local.addLoginSession(login.toEntity())

    suspend fun deleteLoginSession(): Flow<Boolean> =
        local.deleteLoginSession()
}