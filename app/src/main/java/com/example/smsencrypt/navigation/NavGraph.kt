package com.example.smsencrypt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.smsencrypt.MainScreen
import com.example.smsencrypt.MessageScreen
import com.example.smsencrypt.PermissionScreen


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Permission.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
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
            MainScreen(navController)
        }
        composable(route =Screen.Message.route+"{sender}",
            arguments = listOf(
                navArgument(name = "sender"){
                    type = NavType.StringType
                }
            )

        ){sender->
            sender.arguments?.getString("sender")?.let { MessageScreen(Sender = it) }
        }
    }
}