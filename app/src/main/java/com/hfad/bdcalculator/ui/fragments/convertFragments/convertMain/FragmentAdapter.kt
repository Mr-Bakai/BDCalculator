package com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hfad.bdcalculator.ui.fragments.convertFragments.area.AreaFragment
import com.hfad.bdcalculator.ui.fragments.convertFragments.data.DataFragment
import com.hfad.bdcalculator.ui.fragments.convertFragments.length.LengthFragment
import com.hfad.bdcalculator.ui.fragments.convertFragments.mass.MassFragment
import com.hfad.bdcalculator.ui.fragments.convertFragments.speed.SpeedFragment
import com.hfad.bdcalculator.ui.fragments.convertFragments.temperature.TemperatureFragment
import com.hfad.bdcalculator.ui.fragments.convertFragments.volume.VolumeFragment

class FragmentAdapter(
    fragment: Fragment,
    var clickTypeLiveData: MutableLiveData<ConvertHomeViewModel.ClickType>,
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AreaFragment(clickTypeLiveData)
            1 -> LengthFragment(clickTypeLiveData)
            2 -> TemperatureFragment(clickTypeLiveData)
            3 -> VolumeFragment(clickTypeLiveData)
            4 -> MassFragment(clickTypeLiveData)
            5 -> DataFragment(clickTypeLiveData)
            6 -> SpeedFragment(clickTypeLiveData)
            else -> Fragment()
        }
    }
}