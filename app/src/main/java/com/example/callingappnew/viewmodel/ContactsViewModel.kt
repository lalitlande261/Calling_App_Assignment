package com.example.callingappnew.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.callingappnew.data.model.Contact
import com.example.callingappnew.data.repository.CallRepository

class ContactsViewModel(private val repo: CallRepository): ViewModel() {

    var contacts by mutableStateOf<List<Contact>>(emptyList())

    fun loadContacts(){
        contacts = repo.getContacts()
    }

    fun callContact(number:String){
        repo.makeCall(number = number)
    }


}