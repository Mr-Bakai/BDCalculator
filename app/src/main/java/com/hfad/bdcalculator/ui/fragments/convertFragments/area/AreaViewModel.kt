package com.hfad.bdcalculator.ui.fragments.convertFragments.area

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hfad.bdcalculator.R
import com.hfad.bdcalculator.core.ui.base.BaseViewModel
import com.hfad.bdcalculator.data.repository.MainRepo
import com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain.ConvertHomeViewModel

typealias clickType = ConvertHomeViewModel.ClickType
typealias clickTypeLD = MutableLiveData<ConvertHomeViewModel.ClickType>

class AreaViewModel(var repo: MainRepo) : BaseViewModel() {

    enum class ClickType(
        var viewValue: String? = null,
        var viewType: String? = null,
        var data: Double? = null
    ) {
        ACRES("Acres (ac)", "ac", 0.0002471054),
        AREA("Ares (a)", "a", 0.01),
        HECTARES("Hectares (ha)", "ha", 0.0001),
        SQUARE_CEN("Square centimeters (cm²)", "cm²", 10000.0),
        SQUARE_FEET("Square feet (ft²)", "ft²", 10.7639104167),
        SQUARE_INCHES("Square inches (in²)", "in²", 1550.0031000062),
        SQUARE_METERS("Square meters (m²)", "m²", 1.0);

        companion object {
            fun operation(value: String?) = values().find { it.viewValue == value }
        }
    }

    var typedLiveData: MutableLiveData<String> = MutableLiveData()
    var resultLiveData: MutableLiveData<String> = MutableLiveData()

    var textTopAbbLiveData: MutableLiveData<String> = MutableLiveData()
    var textBottomAbbLiveData: MutableLiveData<String> = MutableLiveData()


    private var oneType: ClickType? = null
    private var twoType: ClickType? = null

    private val _eventEditClicked = MutableLiveData<Boolean>()
    val eventEditClicked: LiveData<Boolean> = _eventEditClicked

    init {
        _eventEditClicked.value = true
    }

    private var typedOnes = String()
    private var numToConvert = String()
    private var from = String()
    private var to = String()

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
            R.id.backspace -> onBackSpace()
        }
    }

    private fun onClear() {
        if (!typedOnes.isNullOrBlank()) {
            typedOnes = String()
            resultLiveData.value = String()
        }
    }

    private fun onBackSpace() {
        typedOnes = typedOnes.dropLast(1)
    }

    private fun dataSend() {
        typedLiveData.value = typedOnes
    }

    fun swapData(digit: String?, f: String?, t: String?) {
        if (digit != null) numToConvert = digit
        if (f != null) from = f
        if (t != null) to = t

        if (!numToConvert.isNullOrBlank()) {
            if (repo.isEditFocused) convertFromTopToBottom() else convertFromBottomToTop()
        }
    }

    private fun convert(
        x: Double,
        converter: (Double) -> Double
    ): Double {
        return converter(x)
    }

    fun onEditBoolean(b: Boolean) {
        repo.isEditFocused = b
    }

    fun onEditTopEnable() {
        _eventEditClicked.value = true
    }

    fun onEditBottomEnable() {
        _eventEditClicked.value = false
    }

    private fun convertFromTopToBottom() {
        resultLiveData.value = convert(numToConvert.toDouble(), FormulaProvider().provide(from, to)).toString()
    }

    private fun convertFromBottomToTop() {
        resultLiveData.value = convert(numToConvert.toDouble(), FormulaProvider().provide(to, from)).toString()
    }

    fun selectTopUnit(topUnit: String) {
        oneType = ClickType.operation(topUnit)
    }

    fun selectBottomUnit(bottomUnit: String) {
        twoType = ClickType.operation(bottomUnit)
    }

    fun selectTopAbb() {
        textTopAbbLiveData.value = ClickType.operation(from)?.viewType
    }

    fun selectBottomAbb() {
        textBottomAbbLiveData.value = ClickType.operation(to)?.viewType
    }
}