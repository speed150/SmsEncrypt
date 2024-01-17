package com.example.smsencrypt


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.smsencrypt.components.NumberView
import com.example.smsencrypt.model.SMSMessage
import com.example.smsencrypt.navigation.Screen
import com.example.smsencrypt.viewmodel.SMSviewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController,viewModel: SMSviewmodel) {
viewModel.updateAllMessages(context = LocalContext.current)
    val allMessages = viewModel.allMessages.value
    val result = remember {
        mutableMapOf<String,List<SMSMessage>>()
    }

        val sth = allMessages!!.toList().sortedByDescending{ it.second.last().date}
    //sortowanie listy względem dat
        val a = sth.map{ it.first to it.second}.toMap()
        result += a
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(route = Screen.NewMessage.route) }) {
                Icon(Icons.Default.Add,contentDescription = "NewMessage" )
            }
        }
    ){
            paddingValues->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            result.forEach{
                    (sender,message)->
                item {
                    NumberView(
                        sender = sender,
                        message =message.last().message,
                        navController=navController
                    )
                }
            }

        }
    }
}

