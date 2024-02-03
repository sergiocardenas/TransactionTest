package com.active.transactiontest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.active.transactiontest.data.entity.LoginEntity
import com.active.transactiontest.data.entity.TransactionEntity

@Dao
interface TransactionDao {

    @Query("SELECT * FROM TransactionEntity")
    fun getAllTransaction(): List<TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: TransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLogin(login: LoginEntity)

    @Query("SELECT COUNT(*) FROM LoginEntity")
    fun getLoginCount(): Int

    @Query("DELETE FROM LoginEntity")
    fun deleteLoginSession(): Int
}

