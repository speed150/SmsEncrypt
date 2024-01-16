package com.example.smsencrypt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.smsencrypt.MainScreen
import com.example.smsencrypt.MessageScreen
import com.example.smsencrypt.NewMessageView
import com.example.smsencrypt.PermissionScreen
import com.example.smsencrypt.viewmodel.SMSviewmodel


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel:SMSviewmodel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Permission.route
    ) {
        composable(route = Screen.Permission.route) {
            PermissionScreen(
                onPermissionGranted = {
                    navController.popBackStack()
                    navController.navigate(Screen.Main.route)
                }
            )
        }
        composable(route = Screen.Main.route) {
            MainScreen(navController,viewModel)
        }
        composable(route =Screen.Message.route+"{sender}",
            arguments = listOf(
                navArgument(name = "sender"){
                    type = NavType.StringType
                }
            )

        ){sender->
            sender.arguments?.getString("sender")?.let { MessageScreen(Sender = it,navController,viewModel) }
        }
        composable( route=Screen.NewMessage.route){
            NewMessageView(navController,viewModel)
        }
    }
}