package com.active.transactiontest.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TransactionEntity")
data class TransactionEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "value") val value : Int = 0,
    @ColumnInfo(name = "concept") val concept : String = "",
    @ColumnInfo(name = "withdraw") val withdraw: Boolean = false
)
