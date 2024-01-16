package com.example.smsencrypt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.smsencrypt.navigation.SetupNavGraph
import com.example.smsencrypt.ui.theme.SmsEncryptTheme
import com.example.smsencrypt.viewmodel.SMSviewmodel

class MainActivity : ComponentActivity() {
    val sharedViewmodel: SMSviewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmsEncryptTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController , viewModel = sharedViewmodel)

            }
        }
    }
}