package com.hfad.bdcalculator

import android.app.Application
import androidx.room.Room
import com.hfad.bdcalculator.data.HistoryDatabase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        HistoryDatabase.instance = Room.databaseBuilder(applicationContext, HistoryDatabase::class.java, "history").build()
    }
}
