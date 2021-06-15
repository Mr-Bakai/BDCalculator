package com.hfad.bdcalculator.ui.fragments.convertFragments.volume
import androidx.lifecycle.MutableLiveData
import com.hfad.bdcalculator.databinding.FragmentConvertHomeBinding
import com.hfad.bdcalculator.databinding.FragmentVolumeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.BaseConvertHomeFragment
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.ConvertHomeViewModel

class VolumeFragment(
    clickTypeLiveData: MutableLiveData<ConvertHomeViewModel.ClickType>,
) : BaseConvertHomeFragment<FragmentVolumeBinding>(clickTypeLiveData,FragmentVolumeBinding::inflate) {

    override val viewModel: VolumeViewModel by viewModel()

    override fun setupLiveData() {
        clickTypeLiveData.observe(viewLifecycleOwner, {

        })
    }

    override fun setupUI() {
    }
}