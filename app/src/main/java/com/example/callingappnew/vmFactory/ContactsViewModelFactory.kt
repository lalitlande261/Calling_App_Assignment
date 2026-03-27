package com.example.callingappnew.vmFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.callingappnew.data.repository.CallRepository
import com.example.callingappnew.viewmodel.ContactsViewModel


class ContactsViewModelFactory(
    private val repo: CallRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsViewModel(repo) as T
    }
}