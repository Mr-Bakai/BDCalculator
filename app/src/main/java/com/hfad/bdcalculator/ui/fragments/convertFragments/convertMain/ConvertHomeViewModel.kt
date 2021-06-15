package com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain

import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import com.hfad.bdcalculator.R
import com.hfad.bdcalculator.core.ui.base.BaseViewModel

class ConvertHomeViewModel : BaseViewModel() {

    var clickTypeLiveData = MutableLiveData<ClickType>()

    enum class ClickType(@IdRes var viewId: Int, var viewType: String? = null) {
        ZERO(R.id.zero, "0"),
        ONE(R.id.one, "1"),
        TWO(R.id.two, "2"),
        THREE(R.id.three, "3"),
        FOUR(R.id.four, "4"),
        FIVE(R.id.five, "5"),
        SIX(R.id.six, "6"),
        SEVEN(R.id.seven, "7"),
        EIGHT(R.id.eight, "8"),
        NINE(R.id.nine, "9"),

        ARROW_UP(R.id.textArrowUp),
        ARROW_DOWN(R.id.textArrowDown),
        BACKSPACE(R.id.backspace),
        PLUS_MINUS(R.id.plusMinus),
        COMMA(R.id.comma),
        C(R.id.c);

        companion object {
            fun operation(@IdRes value: Int?) = values().find { it.viewId == value }
        }
    }

    var typedOnes = ""
    var result = ""


    fun eachClick(@IdRes id: Int?) {
        clickTypeLiveData.value = ClickType.operation(id)

//        if (operation?.viewType != null) {
//            operationClick(operation)
//        } else {
//            nonOperation(operation?.viewId)
//        }
//        dataSend()
    }

    private fun operationClick(clickedOne: ClickType) {

//        typedOnes += clickedOne.viewType

    }

    private fun nonOperation(viewId: Int?) {

    }

    private fun dataSend() {
//        clickTypeLiveData.value = typedOnes
    }
}