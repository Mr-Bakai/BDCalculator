package com.hfad.bdcalculator.ui.fragments.convertFragments.area

import androidx.lifecycle.MutableLiveData
import com.hfad.bdcalculator.R
import com.hfad.bdcalculator.core.ui.base.BaseViewModel
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.ConvertHomeViewModel
import java.text.NumberFormat

typealias clickType = ConvertHomeViewModel.ClickType
typealias clickTypeLD = MutableLiveData<ConvertHomeViewModel.ClickType>


class AreaViewModel : BaseViewModel() {

    var typedLiveData: MutableLiveData<String> = MutableLiveData()
    var resultLiveData: MutableLiveData<String> = MutableLiveData()

    var typedOnes = ""

    private var numToConvert = 0.0
    private var from = ""
    private var to = ""

    fun receiveClickType(operation: clickType) {
        if (operation.viewType != null) {
            operationClick(operation)
        } else {
            nonOperation(operation)
        }
        dataSend()
    }

    private fun operationClick(operation: clickType) {
        typedOnes += operation.viewType
    }

    private fun nonOperation(operation: clickType) {
        when (operation.viewId) {
            R.id.c -> onClear()
        }
    }

    private fun onClear() {
        if (!typedOnes.isNullOrBlank()) {
            typedOnes = String()
            resultLiveData.value = String()
        }
    }

    private fun dataSend() {
        typedLiveData.value = typedOnes
    }

    fun passData(digit: Double?, f: String?, t: String?) {

        if (digit != null) numToConvert = digit
        if (f != null) from = f
        if (t != null) to = t

        resultLiveData.value = NumberFormat.getInstance().format(convert(numToConvert, FormulaProvider().provide(from, to))).toString()
    }

    private fun convert(
        x: Double,
        converter: (Double) -> Double
    ): Double {
        return converter(x)
    }
}