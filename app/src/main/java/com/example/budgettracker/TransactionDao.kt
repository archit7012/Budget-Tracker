package com.example.budgettracker

import androidx.room.*
import androidx.room.Dao

@Dao
interface TransactionDao {

    @Query("SELECT * from transactions")
    fun getAll():List<Transaction>

    @Insert
    fun insertAll(vararg transaction: Transaction)

    @Delete
    fun delete(transaction: Transaction)

    @Update
    fun update(vararg transaction: Transaction)

}