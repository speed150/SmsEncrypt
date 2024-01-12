package com.example.smsencrypt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smsencrypt.components.SMSTextBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewMessageView(navController:NavController) {
    var RNumber by remember { mutableStateOf("")
    }

    Scaffold(topBar = {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(5.dp),
            Arrangement.SpaceAround
        ) {
            val pattern = remember { Regex("^\\d+\$") }
            OutlinedTextField(value =RNumber,
                onValueChange ={
                    if (it.isEmpty() || it.matches(pattern)) {
                    RNumber = it
                    }
                               },
                placeholder = {
                    Text(
                        text = "Wpisz Numer Telefonu"
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
        }
    },
        bottomBar = {
            SMSTextBar(RNumber,navController)
        }
    ){
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

        }

    }
}
