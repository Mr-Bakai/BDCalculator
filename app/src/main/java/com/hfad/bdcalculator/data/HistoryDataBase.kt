package com.hfad.bdcalculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [History::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var instance: HistoryDatabase? = null

        fun getInstance(context: Context): HistoryDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context.applicationContext, HistoryDatabase::class.java, "history_database").build()
                }
            }
            return instance!!
        }
    }
}