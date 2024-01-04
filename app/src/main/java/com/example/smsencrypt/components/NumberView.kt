package com.example.smsencrypt.components


import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import com.example.smsencrypt.navigation.Screen

@Composable
fun NumberView(
    sender:String,
    message: String,
    navController: NavController
){
    Card(
        modifier= Modifier
            .padding(5.dp)
            .wrapContentSize()
            .clickable {
                //TODO wchodzenie w wiadomości z danego numeru[threadID]
                navController.navigate(route = Screen.Message.route)
            },
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
//                Text(text = sender)
                val text = if (message.length>20){
                    message.substring(0,16)+"..."
                }
                else{
                    message
                }
                Text(text = sender, fontSize = 24.sp)
                Text(text =text , fontSize = 12.sp)

            }

        }
        
    }
}