package com.active.transactiontest.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LoginEntity")
data class LoginEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val username : String = "",
    @ColumnInfo(name = "password")
    val password : String = "",
)
