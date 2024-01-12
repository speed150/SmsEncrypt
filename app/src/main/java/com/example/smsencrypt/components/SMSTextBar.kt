package com.example.smsencrypt.components

import android.content.Context
import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smsencrypt.model.encrypt
import com.example.smsencrypt.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SMSTextBar(number :String, navController: NavController) {
    val nr=number
    var text by remember { mutableStateOf("") }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(5.dp, 5.dp))
            ,Arrangement.SpaceAround

            ) {
            val context = LocalContext.current

        OutlinedTextField(modifier = Modifier.fillMaxWidth(0.75F),
            shape= RoundedCornerShape(25),
            placeholder = {
                Text(text = "SMS")
                          },
            value = text,
            onValueChange ={text=it},
            maxLines = 4)
            IconButton(onClick = {run{SendMessage(nr, text, context)
                text = ""
                navController.navigate(route = Screen.Message.route+nr)
            }},
                Modifier
                    .size(55.dp)
                    .clip(RoundedCornerShape(40))
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Icon(Icons.Rounded.ArrowForward, contentDescription = "Send")
            }
         }
    }

    

fun SendMessage(phoneNumber: String, message: String,context:Context) {

    if (phoneNumber.length != 9){
        Toast.makeText(context, "błędny numer telefonu", Toast.LENGTH_SHORT).show()
    }
    else if (message.isEmpty()){
        Toast.makeText(context, "wiadomość jest pusta", Toast.LENGTH_SHORT).show()
    }
    else{
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phoneNumber, null, encrypt( message), null, null)
    }
}