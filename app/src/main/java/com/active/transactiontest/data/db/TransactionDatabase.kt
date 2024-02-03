package com.active.transactiontest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.active.transactiontest.data.entity.LoginEntity
import com.active.transactiontest.data.entity.TransactionEntity


@Database(
    entities = [
        TransactionEntity::class,
        LoginEntity::class
    ],
    version = 1,
    exportSchema = false,
)

abstract class TransactionDatabase : RoomDatabase() {
    abstract fun TransactionDao(): TransactionDao
}