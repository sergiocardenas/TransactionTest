package com.active.transactiontest.domain.usecases.imp

import com.active.transactiontest.domain.model.LoginModel
import com.active.transactiontest.domain.repository.TransactionRepository
import com.active.transactiontest.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCaseImp @Inject constructor(
    private val repository: TransactionRepository,
): LoginUseCase {

    override suspend fun hasLoginSession(): Flow<Boolean> =
        repository.hasLoginSession()

    override suspend fun addLoginSession(login: LoginModel): Flow<Boolean> =
        repository.addLoginSession(login)

    override suspend fun deleteLoginSession(): Flow<Boolean> =
        repository.deleteLoginSession()
}
