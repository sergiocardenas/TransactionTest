package com.active.transactiontest.domain.usecases

import com.active.transactiontest.domain.model.LoginModel
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {

    suspend fun hasLoginSession(): Flow<Boolean>

    suspend fun addLoginSession(login: LoginModel): Flow<Boolean>

    suspend fun deleteLoginSession(): Flow<Boolean>
}
