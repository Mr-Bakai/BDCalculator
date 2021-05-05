package com.hfad.bdcalculator.ui.fragments.home
import androidx.lifecycle.MutableLiveData
import com.example.core.ui.base.BaseViewModel
import com.hfad.bdcalculator.R
import io.kaen.dagger.DeprecateParser

class HomeViewModel : BaseViewModel() {

    enum class ClickType(var viewId: Int, var viewType: String? = null) {
        ZERO(R.id.zero,               "0"),
        ONE(R.id.one,                 "1"),
        TWO(R.id.two,                 "2"),
        THREE(R.id.three,             "3"),
        FOUR(R.id.four,               "4"),
        FIVE(R.id.five,               "5"),
        SIX(R.id.six,                 "6"),
        SEVEN(R.id.seven,             "7"),
        EIGHT(R.id.eight,             "8"),
        NINE(R.id.nine,               "9"),
        PARENTHESES(R.id.parentheses, "()"),
        PERCENTAGE(R.id.percentage,   "%"),
        DIVISION(R.id.div,            "/"),
        MULTIPLY(R.id.multiply,       "*"),
        MINUS(R.id.minus,             "-"),
        ADDITION(R.id.addition,       "+"),
        EQUAL(R.id.equal,             "="),
        COMMA(R.id.comma,             ","),
        DELETE(R.id.c);

        companion object {
            fun operation(value: Int?) = values().find { it.viewId == value }
        }
    }

    var fieldLiveData: MutableLiveData<String> = MutableLiveData()
    var resultLiveData: MutableLiveData<String> = MutableLiveData()
    var fieldString = ""
    var result = ""

    fun eachClick(viewId: Int?) {
        val operation = ClickType.operation(viewId)
        if (operation?.viewType != null && operation.viewType != "=") {
            operationClick(operation)
        } else {
            nonOperation(operation?.viewId)
        }
        if (operation?.viewType == "="){
            if (!result.isNullOrBlank()) fieldString = result
            result = ""
            resultLiveData.value = result
        }
        dataSend()
    }

    private fun nonOperation(viewId: Int?) {
        if (viewId == ClickType.DELETE.viewId) {
            fieldString = ""
        }
    }

    private fun operationClick(clickType: ClickType?) {
        fieldString += clickType?.viewType

        result = DeprecateParser().evaluateExpression(fieldString).toString()

        resultLiveData.value = result
    }

    private fun dataSend() {
        if (fieldString.isNotEmpty()) {
            fieldLiveData.value = fieldString
        } else {
            fieldLiveData.value = ""
        }
    }
}