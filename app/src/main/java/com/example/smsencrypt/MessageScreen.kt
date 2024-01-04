package com.example.smsencrypt

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import com.example.smsencrypt.components.MessageView
import com.example.smsencrypt.components.SenderView
import com.example.smsencrypt.model.SMSMessage
import com.example.smsencrypt.model.parseDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
//albo ponowne czytanie listy sms dzięki thread albo przekazanie całej rozmowy dict
fun MessageScreen(thread:Int) {
//    val context= LocalContext.current
    val threadMessages= remember { mutableStateMapOf<String, List<SMSMessage>>() }
//    LaunchedEffect(key1 = Unit ){
//        val messages =
//            readMessages(context = context, type = "inbox") + readMessages(
//                context = context,
//                type = "sent"
//            )
//        threadMessages += messages.sortedBy { it.date }.filter { it.thread== thread }.groupBy { it.sender}
//    }
    Text(text = "$thread")
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){threadMessages
        .forEach { (sender, messages) ->
        stickyHeader(key = sender) {
            SenderView(sender = sender)
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
