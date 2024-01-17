package com.example.smsencrypt

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.smsencrypt.viewmodel.SMSviewmodel
class SmsReceiver(private val viewModel: SMSviewmodel) : BroadcastReceiver() {
    //broadcast reciever
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action=="android.provider.Telephony.SMS_RECEIVED"){
            if (context != null) {
                viewModel.updateAllMessages(context)
            }
        }

    }
}