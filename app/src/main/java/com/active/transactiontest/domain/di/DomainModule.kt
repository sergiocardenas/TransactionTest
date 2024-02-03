package com.active.transactiontest.domain.di


import com.active.transactiontest.data.datasource.LocalDataSource
import com.active.transactiontest.domain.repository.TransactionRepository
import com.active.transactiontest.domain.usecases.LoginUseCase
import com.active.transactiontest.domain.usecases.TransactionUseCase
import com.active.transactiontest.domain.usecases.imp.LoginUseCaseImp
import com.active.transactiontest.domain.usecases.imp.TransactionUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule() {

    @Singleton
    @Provides
    fun provideTransactionRepository(dataSource: LocalDataSource): TransactionRepository =
        TransactionRepository(dataSource)

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: TransactionRepository) : LoginUseCase =
        LoginUseCaseImp(repository)

    @Singleton
    @Provides
    fun provideTransactionUseCase(repository: TransactionRepository) : TransactionUseCase =
        TransactionUseCaseImp(repository)

}