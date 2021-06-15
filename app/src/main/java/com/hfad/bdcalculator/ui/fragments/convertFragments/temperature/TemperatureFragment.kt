package com.hfad.bdcalculator.ui.fragments.convertFragments.temperature
import androidx.lifecycle.MutableLiveData
import com.hfad.bdcalculator.databinding.FragmentConvertHomeBinding
import com.hfad.bdcalculator.databinding.FragmentTemperatureBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.BaseConvertHomeFragment
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.ConvertHomeViewModel

class TemperatureFragment(
    clickTypeLiveData: MutableLiveData<ConvertHomeViewModel.ClickType>,
) : BaseConvertHomeFragment<FragmentTemperatureBinding>(clickTypeLiveData,FragmentTemperatureBinding::inflate) {

    override val viewModel: TemperatureViewModel by viewModel()

    override fun setupLiveData() {
        clickTypeLiveData.observe(viewLifecycleOwner, {

        })
    }

    override fun setupUI() {
    }
}