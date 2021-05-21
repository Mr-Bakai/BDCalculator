package com.hfad.bdcalculator.ui.fragments.home
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.ui.base.BaseViewModel
import com.hfad.bdcalculator.R
import com.hfad.bdcalculator.data.History
import com.hfad.bdcalculator.data.HistoryDatabase
import io.kaen.dagger.ExpressionParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : BaseViewModel() {
    enum class ClickType(var viewId: Int, var viewType: String? = null) {
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
        PARENTHESES(R.id.parentheses),
        PERCENTAGE(R.id.percentage, "%"),
        DIVISION(R.id.div, "/"),
        MULTIPLY(R.id.multiply, "*"),
        MINUS(R.id.minus, "-"),
        ADDITION(R.id.addition, "+"),
        COMMA(R.id.comma, ","),
        CHANGE(R.id.plusMinus, "-/+"),
        BACKSPACE(R.id.backSpace),
        EQUAL(R.id.equal),
        DELETE(R.id.c);

        companion object {
            fun operation(value: Int?) = values().find { it.viewId == value }
            var isContains = false
        }
    }

    var typedLiveData: MutableLiveData<String> = MutableLiveData()
    var resultLiveData: MutableLiveData<String> = MutableLiveData()
    var typedOnes = ""
    var result = ""

    fun eachClick(viewId: Int?) {
        val operation = ClickType.operation(viewId)

        if (operation?.viewType != null) {
            operationClick(operation)
        } else {
            nonOperation(operation?.viewId)
        }
        dataSend()
    }

    private fun nonOperation(viewId: Int?) {

        if (viewId == ClickType.BACKSPACE.viewId) {
            typedOnes = typedOnes.dropLast(1)
        }

        if (viewId == ClickType.DELETE.viewId) {
            typedOnes = ""
            result = ""
        }


        if (viewId == ClickType.EQUAL.viewId) {
            if (!result.isNullOrBlank() && !typedOnes.isNullOrBlank()) {
                typedOnes = ExpressionParser().evaluate(typedOnes).toString()
                resultLiveData.value = String()

                //TODO insert data to room
                viewModelScope.launch(Dispatchers.IO) {
                    HistoryDatabase.instance?.historyDao()?.insertAll(History("test"))
                }
            }
        }

        if (viewId == ClickType.PARENTHESES.viewId) {
            if (!ClickType.isContains) {
                typedOnes = "$typedOnes("
                ClickType.isContains = true
            } else if (ClickType.isContains){
                typedOnes = "$typedOnes)"
                ClickType.isContains = false
            }
        }
    }

    private fun operationClick(clickedOne: ClickType?) {
        if (clickedOne == ClickType.CHANGE) {
            try {
                val numbers = typedOnes.split("%", "/", "*", "+", "-")

                typedOnes = typedOnes.dropLast(numbers.last().length)
                typedOnes = typedOnes + "(" + (numbers.last().toDouble() * -1).toString()
            } catch (e: Exception) {
                resultLiveData.value = String()
            }
        }

        if (clickedOne != ClickType.CHANGE) {
            typedOnes += clickedOne?.viewType
        }

        if (typedOnes.contains("%")
            || typedOnes.contains("/")
            || typedOnes.contains("*")
            || typedOnes.contains("+")
            || typedOnes.contains("-")
        ) {
            try {
                result = ExpressionParser().evaluate(typedOnes).toString()
                resultLiveData.value = result
            } catch (e: Exception) {
                resultLiveData.value = String()
            }
        } else {
            resultLiveData.value = String()
        }
    }

    private fun dataSend() {
        if (typedOnes.isNotEmpty()) {
            typedLiveData.value = typedOnes
        } else {
            typedLiveData.value = ""
            resultLiveData.value = ""
        }
    }
}