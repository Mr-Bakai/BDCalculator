package com.hfad.bdcalculator.ui.fragments.convertFragments.mass
import androidx.lifecycle.MutableLiveData
import com.hfad.bdcalculator.databinding.FragmentConvertHomeBinding
import com.hfad.bdcalculator.databinding.FragmentMassBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.BaseConvertHomeFragment
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.ConvertHomeViewModel

class MassFragment(
    clickTypeLiveData: MutableLiveData<ConvertHomeViewModel.ClickType>,
) : BaseConvertHomeFragment<FragmentMassBinding>(clickTypeLiveData,FragmentMassBinding::inflate) {

    override val viewModel: MassViewModel by viewModel()

    override fun setupLiveData() {
        clickTypeLiveData.observe(viewLifecycleOwner, {

        })
    }

    override fun setupUI() {
    }
}