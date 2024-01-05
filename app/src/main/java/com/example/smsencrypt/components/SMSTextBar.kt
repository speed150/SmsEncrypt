package com.example.smsencrypt.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SMSTextBar() {
    var number=""
    var text by remember { mutableStateOf("") }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(5.dp, 5.dp))
            ,Arrangement.SpaceAround

            ) {


        OutlinedTextField(modifier = Modifier.fillMaxWidth(0.75F), shape= RoundedCornerShape(25), placeholder = { Text(
            text = "SMS"
        )} , value = text, onValueChange ={text=it}, maxLines = 4)
        Button(shape = RoundedCornerShape(30), modifier = Modifier.size(55.dp), onClick = {SendMessage(number)}) {
            Icon(Icons.Default.ArrowForward, contentDescription = "Send")
        }

        }
    }

    

fun SendMessage(number: String){

}