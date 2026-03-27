package com.example.callingappnew.navigation
import kotlinx.serialization.Serializable
@Serializable
sealed class NavRouth(){

    @Serializable
    object Dialer: NavRouth()

    @Serializable
    object Contacts: NavRouth()

    @Serializable
    object CallLogs: NavRouth()


}