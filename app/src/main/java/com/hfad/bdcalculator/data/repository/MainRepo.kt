package com.hfad.bdcalculator.data.repository

import com.hfad.bdcalculator.data.History
import com.hfad.bdcalculator.data.HistoryDatabase

class MainRepo(var db: HistoryDatabase) {

    suspend fun insertModel(history: History) {
        db.historyDao().insertAll(history)
    }

    fun readAllData() = db.historyDao().getAll()
}