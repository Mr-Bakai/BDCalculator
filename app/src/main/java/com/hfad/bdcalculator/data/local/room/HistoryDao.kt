package com.hfad.bdcalculator.data.local.room
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history_table")
    fun getAll(): LiveData<List<History>>

    @Insert
    suspend fun insertAll(vararg history: History)

    @Delete
    fun delete(vararg history: History)
    
    @Query("DELETE FROM history_table")
    suspend fun deleteAll()

//    @Query("SELECT result, typedOnes  FROM history_table")
//    fun getResult()

    //Fragment Manager
    //Kotlin in Action
    //КодВарс
    //Библиотека программиста
}