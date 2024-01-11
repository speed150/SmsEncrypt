package com.example.smsencrypt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smsencrypt.components.SMSTextBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewMessageView() {
    var RNumber =""

    Scaffold(topBar = {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(5.dp),
            Arrangement.SpaceAround
        ) {
            OutlinedTextField(value =RNumber,
                onValueChange ={RNumber=it},
                placeholder = {
                    Text(
                        text = "Wpisz Numer Telefonu"
                    )
                }
            )
        }
    },
        bottomBar = {SMSTextBar(RNumber
        )}){
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

        }

    }
}
