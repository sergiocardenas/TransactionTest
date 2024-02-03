package com.active.transactiontest.data.datasource


import com.active.transactiontest.data.entity.LoginEntity
import com.active.transactiontest.data.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getTransactionList(): Flow<List<TransactionEntity>>

    suspend fun addTransaction(transaction: TransactionEntity): Flow<Boolean>

    suspend fun hasLoginSession(): Flow<Boolean>

    suspend fun addLoginSession(login: LoginEntity): Flow<Boolean>

    suspend fun deleteLoginSession(): Flow<Boolean>
}
