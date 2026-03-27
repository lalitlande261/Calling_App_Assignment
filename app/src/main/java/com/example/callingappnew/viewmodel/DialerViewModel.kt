package com.example.callingappnew.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.callingappnew.data.repository.CallRepository


class DialerViewModel(private val repo: CallRepository): ViewModel() {

    var number by mutableStateOf("")

    fun onNumberClicked(digit:String){
        this.number += digit

    }

    fun onDelete(){
        if (number.isNotEmpty()){
            number = number.dropLast(1)
        }
    }

    fun makeCall(){
        repo.makeCall(number)
    }


}