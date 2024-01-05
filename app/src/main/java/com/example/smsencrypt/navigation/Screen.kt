package com.example.smsencrypt.navigation

sealed class Screen(val route:String){
    object Permission :Screen(route = "permission_screen")
    object Main :Screen(route = "main_screen")
    object Message:Screen(route ="message_screen/")
    object NewMessage:Screen(route = "newMessage_screen")
}