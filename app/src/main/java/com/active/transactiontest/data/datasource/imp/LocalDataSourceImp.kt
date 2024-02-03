package com.active.transactiontest.data.datasource.imp

import com.active.transactiontest.data.datasource.LocalDataSource
import com.active.transactiontest.data.db.TransactionDao
import com.active.transactiontest.data.entity.LoginEntity
import com.active.transactiontest.data.entity.TransactionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val transactionDao: TransactionDao
): LocalDataSource {

    override suspend fun getTransactionList(): Flow<List<TransactionEntity>> = flow {
        val list = withContext(Dispatchers.IO) {
            transactionDao.getAllTransaction()
        }
        emit(list)
    }

    override suspend fun addTransaction(transaction: TransactionEntity): Flow<Boolean> = flow {
        withContext(Dispatchers.IO) {
            transactionDao.insertTransaction(transaction)
        }
        emit(true)
    }

    override suspend fun hasLoginSession(): Flow<Boolean> = flow {
        val result = withContext(Dispatchers.IO) {
            transactionDao.getLoginCount()
        }
        emit(result > 0)
    }

    override suspend fun addLoginSession(login: LoginEntity): Flow<Boolean> = flow {
        withContext(Dispatchers.IO) {
            transactionDao.insertLogin(login)
        }
        emit(true)
    }

    override suspend fun deleteLoginSession(): Flow<Boolean> = flow {
        val result = withContext(Dispatchers.IO) {
            transactionDao.deleteLoginSession()
        }
        emit(result > 0)
    }
}
