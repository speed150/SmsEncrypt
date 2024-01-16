package com.example.smsencrypt

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.smsencrypt.components.MessageView
import com.example.smsencrypt.components.SMSTextBar
import com.example.smsencrypt.components.SenderView
import com.example.smsencrypt.model.SMSMessage
import com.example.smsencrypt.model.parseDate
import com.example.smsencrypt.viewmodel.SMSviewmodel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
//albo ponowne czytanie listy sms dzięki thread albo przekazanie całej rozmowy dict
fun MessageScreen(Sender:String,navController: NavController,viewModel: SMSviewmodel) {

    val threadMessages= remember { mutableStateMapOf<String, List<SMSMessage>>() }
    val b = remember {mutableListOf<SMSMessage>()}


    LaunchedEffect(key1 = Unit){
    val messages  = viewModel.allMessages.value
    if (threadMessages.size>0){
    b += threadMessages.values.first()
    }
    val a = messages!!.filterKeys { it ==Sender }
    threadMessages += a
    }
    Scaffold(bottomBar = {  SMSTextBar(Sender, navController,false,viewModel)}){
        innerPadding->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),

        ){threadMessages
            .forEach { (sender, messages) ->
                stickyHeader(key = sender) {
                    SenderView(sender = Sender)
                }
                messages.groupBy { it.date.parseDate().split(" ").first() }
                    .forEach { (date, smsMessage) ->
                        item {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .alpha(0.38f),
                                text = date,
                                textAlign = TextAlign.Center,
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        items(
                            items = smsMessage,
                            key = { it.date }
                        ) {
                            MessageView(message = it)

                        }
                    }

            }

        }
    }



    }



