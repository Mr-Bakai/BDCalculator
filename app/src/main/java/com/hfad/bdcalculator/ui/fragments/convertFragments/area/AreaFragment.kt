package com.hfad.bdcalculator.ui.fragments.convertFragments.area

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.hfad.bdcalculator.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.hfad.bdcalculator.databinding.FragmentAreaBinding
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.BaseConvertHomeFragment

@SuppressLint("ClickableViewAccessibility")

class AreaFragment(
    clickTypeLiveData: clickTypeLD,
) : BaseConvertHomeFragment<FragmentAreaBinding>(
    clickTypeLiveData,
    FragmentAreaBinding::inflate),
    AdapterView.OnItemSelectedListener {

    override val viewModel: AreaViewModel by viewModel()

    override fun setupLiveData() {

        clickTypeLiveData.observe(viewLifecycleOwner, {
            viewModel.receiveClickType(it)
        })

        viewModel.typedLiveData.observe(viewLifecycleOwner, {
            if (viewModel.repo.isEditFocused) onTextTypedTop(it) else onTextTypedBottom(it)
            if (!it.isNullOrBlank()) viewModel.swapData(digit = it, null, null)
        })

        viewModel.resultLiveData.observe(viewLifecycleOwner, {
            if (viewModel.repo.isEditFocused) onTextResultBottom(it) else onTextResultTop(it)
        })

        viewModel.eventEditClicked.observe(viewLifecycleOwner, {
            viewModel.onEditBoolean(it)
        })

        viewModel.textTopAbbLiveData.observe(viewLifecycleOwner, {
            binding.textTopAbbreviation.text = it
        })

        viewModel.textBottomAbbLiveData.observe(viewLifecycleOwner, {
            binding.textBottomAbbreviation.text = it
        })
    }

    override fun setupUI() {
        initSpinners()
        setOnTouch()
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

    private fun setOnTouch() {

        binding.textTop.setOnTouchListener(View.OnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) viewModel.onEditTopEnable()
            return@OnTouchListener true
        })

        binding.textBottom.setOnTouchListener(View.OnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) viewModel.onEditBottomEnable()
            return@OnTouchListener true
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent?.id == binding.spinnerTop.id) {
            viewModel.swapData(f = parent.getItemAtPosition(position).toString(), digit = null, t = null)
            viewModel.selectTopAbb()
            viewModel.selectTopUnit(parent.getItemAtPosition(position).toString())

        } else {
            if (parent != null) {
                viewModel.swapData(f = null, digit = null, t = parent.getItemAtPosition(position).toString())
                viewModel.selectBottomAbb()
                viewModel.selectBottomUnit(parent.getItemAtPosition(position).toString())
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun onTextResultTop(it: String?) {
        binding.textTop.setText(it.toString())
    }

    private fun onTextResultBottom(it: String?) {
        binding.textBottom.setText(it.toString())
    }

    private fun onTextTypedTop(it: String?) {
        binding.textTop.setText(it.toString())
    }

    private fun onTextTypedBottom(it: String?) {
        binding.textBottom.setText(it.toString())
    }
}
