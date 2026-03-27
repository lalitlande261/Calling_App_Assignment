package com.example.callingappnew.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.callingappnew.bottomBar.NavigationBar
import com.example.callingappnew.data.repository.CallRepository
import com.example.callingappnew.presentation.componants.ContactItem
import com.example.callingappnew.viewmodel.ContactsViewModel
import com.example.callingappnew.vmFactory.ContactsViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(navController: NavHostController) {

    val context = LocalContext.current
    val repo = CallRepository(context)

    val viewModel: ContactsViewModel = viewModel(
        factory = ContactsViewModelFactory(repo)
    )

    val contacts = viewModel.contacts

    // Load contacts once
    LaunchedEffect(Unit) {
        viewModel.loadContacts()
    }

    Scaffold(
        bottomBar = {
            NavigationBar(navController, key = "Contacts")
        }
            ,topBar = { TopAppBar(title = {Text("Contacts", fontWeight = FontWeight.SemiBold, fontSize = 22.sp)}) }



    ) { padding ->

        if (contacts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No contacts found")
            }
        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                items(contacts) { contact ->
                    ContactItem(contact = contact) {
                        viewModel.callContact(contact.number)
                    }
                }
            }
        }
    }
}