package com.example.callingappnew

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.callingappnew.navigation.AppNavigation
import com.example.callingappnew.permission.PermissionManager
import com.example.callingappnew.viewmodel.CallStateManager

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lateinit var permissionManager: PermissionManager

        val launcher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            val allGranted = permissions.values.all { it }

            if (!allGranted) {
                Toast.makeText(
                    this,
                    "Permissions are required for app functionality",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        permissionManager = PermissionManager(this, launcher)

        setContent {

            val context = LocalContext.current


            val callStateManager = remember {
                CallStateManager(context)
            }

            LaunchedEffect(Unit) {
                if (!permissionManager.hasPermissions()) {
                    permissionManager.requestPermissions()
                }

                callStateManager.startListening()
            }


            AppNavigation()


        }
    }
}