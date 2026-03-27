package com.example.callingappnew.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.callingappnew.presentation.CallLogsScreen
import com.example.callingappnew.presentation.ContactsScreen
import com.example.callingappnew.presentation.DialerScreen


@Composable
fun AppNavigation(){

    val navController = rememberNavController()
    NavHost(navController = navController,startDestination = NavRouth.Dialer ) {
        composable<NavRouth.Dialer> {
            DialerScreen(navController)

        }
        composable<NavRouth.CallLogs> {
            CallLogsScreen(navController)

        }
        composable<NavRouth.Contacts> {
            ContactsScreen(navController)

        }

    }


}