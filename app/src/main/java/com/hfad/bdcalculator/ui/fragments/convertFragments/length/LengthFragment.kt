package com.hfad.bdcalculator.ui.fragments.convertFragments.length

import androidx.lifecycle.MutableLiveData
import com.hfad.bdcalculator.databinding.FragmentConvertHomeBinding
import com.hfad.bdcalculator.databinding.FragmentLengthBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.BaseConvertHomeFragment
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.ConvertHomeViewModel

class LengthFragment(
    clickTypeLiveData: MutableLiveData<ConvertHomeViewModel.ClickType>,
) : BaseConvertHomeFragment<FragmentLengthBinding>(
    clickTypeLiveData,
    FragmentLengthBinding::inflate
) {

    override val viewModel: LengthViewModel by viewModel()

    override fun setupLiveData() {
        clickTypeLiveData.observe(viewLifecycleOwner, {

        })
    }

    override fun setupUI() {
    }
}