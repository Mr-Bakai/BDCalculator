package com.hfad.bdcalculator.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hfad.bdcalculator.core.ui.base.BaseViewModel
import com.hfad.bdcalculator.R
import com.hfad.bdcalculator.data.local.room.History
import com.hfad.bdcalculator.data.repository.MainRepo
import io.kaen.dagger.ExpressionParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(var repository: MainRepo) : BaseViewModel() {

    enum class ClickType(var viewId: Int,
                         var viewType: String? = null) {
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
        COMMA(R.id.comma, "."),
        CHANGE(R.id.plusMinus, "-/+"),
        BACKSPACE(R.id.backSpace),
        EQUAL(R.id.equal),
        DELETE(R.id.c),
        CLOCK(R.id.imageClock),
        CLEAR_HISTORY(R.id.clearHistory);

        companion object {
            fun operation(value: Int?) = values().find { it.viewId == value }
            var isContains = false
        }
    }

    var typedLiveData: MutableLiveData<String> = MutableLiveData()
    var resultLiveData: MutableLiveData<String> = MutableLiveData()
    var fromRoom: LiveData<List<History>> = repository.readAllData()

    private val _eventEqualClicked = MutableLiveData<Boolean>()
    val eventEqualClicked: LiveData<Boolean> = _eventEqualClicked

    var typedOnes = ""
    var result = ""

    fun setHistory(history: History) {
        typedOnes = history.typedOnes.toString()
        result = history.result.toString()

        typedLiveData.value = typedOnes
        resultLiveData.value = result
    }

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
        when (viewId) {
            ClickType.BACKSPACE.viewId       -> onBackSpace()
            ClickType.DELETE.viewId          -> onDelete()
            ClickType.EQUAL.viewId           -> onEqual()
            ClickType.CLOCK.viewId           -> onHistory()
            ClickType.PARENTHESES.viewId     -> onParentheses()
            ClickType.CLEAR_HISTORY.viewId   -> onClearHistory()
        }
    }

    private fun onClearHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }

    private fun onDelete() {
        typedOnes = ""
        result = ""
    }

    private fun onBackSpace() {
        typedOnes = typedOnes.dropLast(1)
    }

    private fun onParentheses() {
        if (!ClickType.isContains) {
            typedOnes = "$typedOnes("
            ClickType.isContains = true
        } else if (ClickType.isContains) {
            typedOnes = "$typedOnes)"
            ClickType.isContains = false
        }
    }

    private fun onHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            fromRoom = repository.readAllData()
        }
    }

    private fun onEqual() {
        if (!result.isNullOrBlank() && !typedOnes.isNullOrBlank()) {
            onColorGreen()

            val history = History(null, typedOnes = typedOnes)
            typedOnes = if (typedOnes.contains(".")) resultToString() else resultToInt()

            resultLiveData.value = String()
            history.result = typedOnes

            viewModelScope.launch(Dispatchers.IO){
                repository.insertModel(history)
            }
        }
    }

    private fun resultToInt() = ExpressionParser().evaluate(typedOnes).toInt().toString()

    private fun resultToString() = ExpressionParser().evaluate(typedOnes).toString()

    private fun operationClick(clickedOne: ClickType?) {
        onColorBlack()

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
            if (!typedOnes.contains(".")) {
                try {
                    result = ExpressionParser().evaluate(typedOnes).toInt().toString()
                    resultLiveData.value = result
                } catch (e: Exception) {
                    resultLiveData.value = String()
                }
            } else if (typedOnes.contains(".")) {
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
    }

    private fun dataSend() {
        if (typedOnes.isNotEmpty()) {
            typedLiveData.value = typedOnes
        } else {
            typedLiveData.value = ""
            resultLiveData.value = ""
        }
    }

    private fun onColorGreen() {
        _eventEqualClicked.value = true
    }

    private fun onColorBlack() {
        _eventEqualClicked.value = false
    }
}