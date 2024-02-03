package com.active.transactiontest.data.di

import android.content.Context
import androidx.room.Room
import com.active.transactiontest.data.datasource.LocalDataSource
import com.active.transactiontest.data.datasource.imp.LocalDataSourceImp
import com.active.transactiontest.data.db.TransactionDao
import com.active.transactiontest.data.db.TransactionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule() {

    @Singleton
    @Provides
    fun provideTransactionDB(@ApplicationContext appContext: Context): TransactionDatabase {
        return Room.databaseBuilder(
            appContext,
            TransactionDatabase::class.java,
            "reminder-database"
        ).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideTransactionDao(database: TransactionDatabase) = database.TransactionDao()


    @Singleton
    @Provides
    fun provideLocalDataSource(transactionDao: TransactionDao) : LocalDataSource {
        return LocalDataSourceImp(transactionDao)
    }
}