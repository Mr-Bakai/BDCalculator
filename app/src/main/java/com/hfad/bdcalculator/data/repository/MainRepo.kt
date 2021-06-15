package com.hfad.bdcalculator.data.repository

import com.hfad.bdcalculator.data.local.room.History
import com.hfad.bdcalculator.data.local.room.HistoryDatabase

class MainRepo(var db: HistoryDatabase) {

    suspend fun insertModel(history: History) { db.historyDao().insertAll(history) }

    fun readAllData() = db.historyDao().getAll()

    suspend fun deleteAllData() = db.historyDao().deleteAll()
}