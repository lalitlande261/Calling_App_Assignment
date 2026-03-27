package com.example.callingappnew.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.callingappnew.bottomBar.NavigationBar
import com.example.callingappnew.data.repository.CallRepository
import com.example.callingappnew.presentation.componants.KeypadButton
import com.example.callingappnew.viewmodel.DialerViewModel
import com.example.callingappnew.vmFactory.DialerViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialerScreen(navController: NavHostController) {

    val context = LocalContext.current
    val repo = CallRepository(context)

    val viewModel: DialerViewModel = viewModel(
        factory = DialerViewModelFactory(repo)
    )

    val number = viewModel.number

    Scaffold(bottomBar = { NavigationBar(navController, key = "Dialer")

    }
        , topBar = { TopAppBar(title = {Text("Dialer", fontWeight = FontWeight.SemiBold, fontSize = 22.sp)}) }
    ) {

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = number,
                    fontSize = 32.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(64.dp))


                val buttons = listOf(
                    "1", "2", "3",
                    "4", "5", "6",
                    "7", "8", "9",
                    "*", "0", "#"
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.weight(1f)
                ) {
                    items(buttons) { digit ->
                        KeypadButton(digit) {
                            viewModel.onNumberClicked(digit)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {


                    IconButton(onClick = { viewModel.onDelete() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Delete")
                    }

                    FloatingActionButton(
                        onClick = { viewModel.makeCall() },
                        containerColor = Color.Green
                    ) {
                        Icon(Icons.Default.Call, contentDescription = "Call")
                    }

                    Spacer(modifier = Modifier.width(48.dp))
                }
            }
        }
    }
}