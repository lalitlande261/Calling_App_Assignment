package com.example.callingappnew.presentation

import android.content.Context
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.callingappnew.bottomBar.NavigationBar
import com.example.callingappnew.data.repository.CallRepository
import com.example.callingappnew.presentation.componants.CallLogItemUI
import com.example.callingappnew.viewmodel.CallLogsViewModel
import com.example.callingappnew.vmFactory.CallLogsViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallLogsScreen(navController: NavHostController) {

    val context = LocalContext.current
    val repo = CallRepository(context)

    val viewModel: CallLogsViewModel = viewModel(
        factory = CallLogsViewModelFactory(repo)
    )

    val logs = viewModel.logs

    LaunchedEffect(Unit) {
        viewModel.loadLogs()
    }

    Scaffold(
        bottomBar = {
            NavigationBar(navController, key = "CallLogs")
        }
        , topBar = { TopAppBar(title = {Text("CallLogs", fontWeight = FontWeight.SemiBold, fontSize = 22.sp)}) }
    ) { padding ->

        if (logs.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No recent calls")
            }
        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                items(logs) { log ->

                    CallLogItemUI(log = log) {
                        viewModel.callNumber(log.number)
                    }
                }
            }
        }
    }
}

