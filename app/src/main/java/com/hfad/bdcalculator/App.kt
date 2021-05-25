package com.hfad.bdcalculator

import android.app.Application
import com.hfad.bdcalculator.di.dataBaseModule
import com.hfad.bdcalculator.di.repositoryModule
import com.hfad.bdcalculator.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(provideModules())
        }
    }

    private fun provideModules() = listOf(
        dataBaseModule,
        repositoryModule,
        viewModelModule)
}
