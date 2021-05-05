package com.hfad.bdcalculator.ui.fragments.home
import androidx.lifecycle.MutableLiveData
import com.example.core.ui.base.BaseViewModel
import com.hfad.bdcalculator.R

class HomeViewModel : BaseViewModel() {

    enum class ClickType(var viewId: Int, var viewType: String? = null) {
        ONE(R.id.one, "1"),
        TWO(R.id.two, "2"),
        THREE(R.id.three, "3"),
        FOUR(R.id.four, "4"),
        FIVE(R.id.five, "5"),
        SIX(R.id.six, "6"),
        SEVEN(R.id.seven, "7"),
        EIGHT(R.id.eight, "8"),
        NINE(R.id.nine, "9"),
        PARENTHESES(R.id.parentheses, "()"),
        PERCENTAGE(R.id.percentage, "%"),
        DIVISION(R.id.div, "÷"),
        MULTIPLY(R.id.multiply, "x"),
        MINUS(R.id.minus, "—"),
        ADDITION(R.id.addition, "+"),
        EQUAL(R.id.equal, "="),
        DELETE(R.id.c);

        companion object {
            fun operation(value: Int?) = values().find { it.viewId == value }
        }
    }

    var fieldLiveData: MutableLiveData<String> = MutableLiveData()
    var fieldString = ""

    fun eachClick(viewId: Int?) {
        var operation = ClickType.operation(viewId)
        if (operation?.viewType != null) {
            operationClick(operation)
        } else {
            nonOperation(operation?.viewId)
        }
        dataSand()
    }

    private fun nonOperation(viewId: Int?) {
        if (viewId == ClickType.DELETE.viewId) {
            fieldString = ""
        }
    }

    private fun operationClick(clickType: ClickType?) {
        fieldString += clickType?.viewType
    }

    private fun dataSand() {
        if (fieldString.isNotEmpty()) {
            fieldLiveData.value = fieldString
        } else {
            fieldLiveData.value = ""
        }
    }
}