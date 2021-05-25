package com.hfad.bdcalculator.di

import com.example.core.ui.base.BaseViewModel
import com.hfad.bdcalculator.ui.fragments.home.HomeViewModel
import com.hfad.bdcalculator.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}