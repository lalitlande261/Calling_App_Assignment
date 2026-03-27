package com.example.callingappnew.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.callingappnew.navigation.NavRouth

@Composable
fun NavigationBar(navController: NavHostController, key: String) {
    val navItems = listOf(
        NavItems(Icons.Default.Menu,"Dialer", NavRouth.Dialer),
        NavItems(Icons.Default.Call,"CallLogs",NavRouth.CallLogs),
        NavItems(Icons.Default.AccountBox,"Contacts",NavRouth.Contacts)

    )
    NavigationBar {
        navItems.forEach { item ->
            NavigationBarItem(
                selected = item.tittle == key,
                onClick = {
                    navController.navigate(item.navRouth) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.tittle) },
                label = { Text(item.tittle) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Red,
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Black,
                    disabledIconColor = Color.Green

                )


            )
        }
    }

}
data class NavItems(val icon: ImageVector, val tittle: String, val navRouth: NavRouth)