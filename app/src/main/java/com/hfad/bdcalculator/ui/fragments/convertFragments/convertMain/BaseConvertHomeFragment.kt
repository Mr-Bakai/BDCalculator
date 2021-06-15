package com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.ViewBinding
import com.hfad.bdcalculator.core.ui.base.BaseFragment

abstract class BaseConvertHomeFragment<Binding: ViewBinding>(var clickTypeLiveData: MutableLiveData<ConvertHomeViewModel.ClickType>, inflate: (LayoutInflater, ViewGroup?, Boolean) -> Binding): BaseFragment<Binding>(inflate)