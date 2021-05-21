package com.hfad.bdcalculator.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(History::class), version = 1)
abstract class HistoryDatabase : RoomDatabase() {
    companion object {
        var instance: HistoryDatabase? = null
    }

    abstract fun historyDao(): HistoryDao
}