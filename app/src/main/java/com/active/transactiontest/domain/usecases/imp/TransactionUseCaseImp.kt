package com.active.transactiontest.domain.usecases.imp

import com.active.transactiontest.domain.model.TransactionModel
import com.active.transactiontest.domain.repository.TransactionRepository
import com.active.transactiontest.domain.usecases.TransactionUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionUseCaseImp @Inject constructor(
    private val repository: TransactionRepository,
): TransactionUseCase {

    override suspend fun getTransactionList(): Flow<List<TransactionModel>> =
        repository.getTransactionList()

    override suspend fun addTransaction(transaction: TransactionModel): Flow<Boolean> =
        repository.addTransaction(transaction)
}
