package com.hfad.bdcalculator.ui.fragments.convertFragments.speed
import androidx.lifecycle.MutableLiveData
import com.hfad.bdcalculator.databinding.FragmentConvertHomeBinding
import com.hfad.bdcalculator.databinding.FragmentSpeedBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.BaseConvertHomeFragment
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.ConvertHomeViewModel

class SpeedFragment(
    clickTypeLiveData: MutableLiveData<ConvertHomeViewModel.ClickType>,
) : BaseConvertHomeFragment<FragmentSpeedBinding>(clickTypeLiveData,FragmentSpeedBinding::inflate){

    override val viewModel: SpeedViewModel by viewModel()

    override fun setupLiveData() {
        clickTypeLiveData.observe(viewLifecycleOwner, {

        })
    }

    override fun setupUI() {
    }
}