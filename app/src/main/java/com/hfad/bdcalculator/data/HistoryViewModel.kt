package com.hfad.bdcalculator.data
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<History>> = MutableLiveData()

    fun addHistory(history: History){
        viewModelScope.launch(Dispatchers.IO) {
            HistoryDatabase.instance?.historyDao()?.insertAll(history)
        }
    }
}