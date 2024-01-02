package com.example.smsencrypt.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smsencrypt.model.SMSMessage

@Composable
fun NumberView(message: SMSMessage){
    Card(
        modifier= Modifier
            .padding(5.dp)
            .wrapContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(5.dp)
        ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ){
            Column(modifier = Modifier.padding(5.dp)) {
                val text = if (message.message.length>20){
                    message.message.substring(0,16)+"..."
                }
                else{
                    message.message
                }
                Text(text = message.sender, fontSize = 24.sp)
                Text(text =text , fontSize = 12.sp)

            }

        }
        
    }
}