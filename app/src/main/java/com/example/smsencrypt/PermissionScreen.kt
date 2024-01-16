package com.example.smsencrypt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@Composable
fun PermissionScreen(onPermissionGranted:()->Unit){

    RequestSmsPermission(onPermissionGranted = onPermissionGranted)


}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestSmsPermission(onPermissionGranted: () -> Unit) {
    val readSmsPermission = rememberPermissionState(
        android.Manifest.permission.READ_SMS
    )
    val sendSmsPermission = rememberPermissionState(
        android.Manifest.permission.SEND_SMS
    )
    val receiveSmsPermission = rememberPermissionState(
        android.Manifest.permission.RECEIVE_SMS
    )
    fun getpermission(){
        readSmsPermission.launchPermissionRequest()
        sendSmsPermission.launchPermissionRequest()
        receiveSmsPermission.launchPermissionRequest()
    }
    if (readSmsPermission.status.isGranted && sendSmsPermission.status.isGranted){
        onPermissionGranted()
    }
    else{
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val textToShow =if (readSmsPermission.status.shouldShowRationale){
                "Aby korzystać z aplikacji potrzbne są uprawnienia"
            }
            else{
                "uprawnienia są wymagane \n zezwól na dostęp"
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 20.dp),
                text = textToShow,
                textAlign = TextAlign.Center
            )
            Button(onClick = { getpermission()}) {
                Text(text = "Przyznaj uprawnienia")
            }

        }
    }

}