package com.hfad.bdcalculator.ui.fragments.convertFragments.area

import android.view.View
import android.widget.AdapterView
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.widget.ArrayAdapter
import com.hfad.bdcalculator.R
import com.hfad.bdcalculator.databinding.FragmentAreaBinding
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.BaseConvertHomeFragment

class AreaFragment(
    clickTypeLiveData: clickTypeLD,
) : BaseConvertHomeFragment<FragmentAreaBinding>(clickTypeLiveData, FragmentAreaBinding::inflate),
    AdapterView.OnItemSelectedListener {

    override val viewModel: AreaViewModel by viewModel()

    override fun setupLiveData() {

        clickTypeLiveData.observe(viewLifecycleOwner, {
            viewModel.receiveClickType(it)
        })

        viewModel.typedLiveData.observe(viewLifecycleOwner, {
            binding.textTop.text = it
            if (!it.isNullOrBlank()) viewModel.passData(digit = it.toDouble(), null, null)
        })

        viewModel.resultLiveData.observe(viewLifecycleOwner, {
            binding.textBottom.text = it.toString()
        })
    }


    override fun setupUI() {
        initSpinners()
    }

    private fun initSpinners() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.area,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerTop.adapter = adapter
            binding.spinnerTop.onItemSelectedListener = this
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.area,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerBottom.adapter = adapter
            binding.spinnerBottom.onItemSelectedListener = this
        }
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        if (parent?.id == binding.spinnerTop.id) {
            viewModel.passData(f = parent.getItemAtPosition(position).toString(), digit = null, t = null)
        } else {
            if (parent != null) {
                viewModel.passData(f = null, digit = null, t = parent.getItemAtPosition(position).toString())
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}