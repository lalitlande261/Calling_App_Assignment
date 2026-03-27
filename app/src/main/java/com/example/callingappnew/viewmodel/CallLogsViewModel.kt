package com.example.callingappnew.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.callingappnew.data.model.CallLogItem
import com.example.callingappnew.data.repository.CallRepository

class CallLogsViewModel(private val repo: CallRepository): ViewModel() {

    var logs by mutableStateOf<List<CallLogItem>>(emptyList())

    fun loadLogs(){
        logs = repo.getAllContacts()
    }

    fun callNumber(number:String){
        repo.makeCall(number)
    }


}