package com.hfad.bdcalculator.di
import com.hfad.bdcalculator.data.repository.MainRepo
import org.koin.dsl.module


val repositoryModule = module {
    single { MainRepo(get()) }
}