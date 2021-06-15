package com.hfad.bdcalculator.di

import com.hfad.bdcalculator.core.ui.base.BaseViewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.area.AreaViewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.ConvertHomeViewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.data.DataViewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.length.LengthViewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.mass.MassViewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.speed.SpeedViewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.temperature.TemperatureViewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.volume.VolumeViewModel
import com.hfad.bdcalculator.ui.fragments.home.HomeViewModel
import com.hfad.bdcalculator.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ConvertHomeViewModel() }
    viewModel { TemperatureViewModel() }
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { LengthViewModel() }
    viewModel { VolumeViewModel() }
    viewModel { SpeedViewModel() }
    viewModel { BaseViewModel() }
    viewModel { AreaViewModel() }
    viewModel { DataViewModel() }
    viewModel { MassViewModel() }
}