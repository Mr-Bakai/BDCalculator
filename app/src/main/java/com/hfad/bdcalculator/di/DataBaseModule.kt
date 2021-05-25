package com.hfad.bdcalculator.di

import com.hfad.bdcalculator.data.HistoryDatabase
import org.koin.dsl.module


val dataBaseModule = module {
    single { HistoryDatabase.getInstance(get()) }
    single { get<HistoryDatabase>().historyDao() }
}