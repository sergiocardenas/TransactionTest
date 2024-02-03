package com.active.transactiontest.domain.usecases

import com.active.transactiontest.domain.model.TransactionModel
import kotlinx.coroutines.flow.Flow

interface TransactionUseCase {
    suspend fun getTransactionList(): Flow<List<TransactionModel>>

    suspend fun addTransaction(transaction: TransactionModel): Flow<Boolean>
}
