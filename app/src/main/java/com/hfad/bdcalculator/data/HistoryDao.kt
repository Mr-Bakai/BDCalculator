package com.hfad.bdcalculator.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history_table")
    fun getAll(): List<History>

    @Insert
    fun insertAll(vararg history: History)

    @Delete
    fun delete(vararg history: History)

    @Query("DELETE FROM history_table")
    fun deleteAll()
}