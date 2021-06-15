package com.hfad.bdcalculator.ui.fragments.convertFragments.data

import androidx.lifecycle.MutableLiveData
import com.hfad.bdcalculator.databinding.FragmentConvertHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.hfad.bdcalculator.databinding.FragmentDataBinding
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.BaseConvertHomeFragment
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.ConvertHomeViewModel

class DataFragment(
    clickTypeLiveData: MutableLiveData<ConvertHomeViewModel.ClickType>,
) : BaseConvertHomeFragment<FragmentDataBinding>(clickTypeLiveData, FragmentDataBinding::inflate) {

    override val viewModel: DataViewModel by viewModel()

    override fun setupLiveData() {
        clickTypeLiveData.observe(viewLifecycleOwner, {
            
        })
    }

    override fun setupUI() {
    }
}