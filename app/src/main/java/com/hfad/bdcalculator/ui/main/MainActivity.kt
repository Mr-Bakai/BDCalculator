package com.hfad.bdcalculator.ui.main
import com.hfad.bdcalculator.core.ui.base.BaseActivity
import com.hfad.bdcalculator.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    ActivityMainBinding::inflate,
    MainViewModel::class.java
) {
    override fun setUpView() {}
}