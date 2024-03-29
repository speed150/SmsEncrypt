package com.example.smsencrypt.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smsencrypt.model.SMSMessage

class SMSviewmodel:ViewModel() {
    private  val _allMessages = MutableLiveData<MutableMap<String,List<SMSMessage>>>()
    val allMessages :LiveData<MutableMap<String,List<SMSMessage>>> get()=_allMessages
    //mapa wiadomości w postaci <Sender,List<Wszystkie wiadomości odnego nadawcy>>
    fun updateAllMessages(context: Context){
        val messages = readMessages(context = context, type = "inbox") +
                readMessages(context,"sent")
        val msg = mutableMapOf<String,List<SMSMessage>>()
        msg += messages.sortedBy { it.date }.groupBy { it.sender }
        _allMessages.value = msg
    }
}
fun readMessages(context: Context, type: String): List<SMSMessage> {
 //czytanie wszystkich wiadomości
    val messages = mutableListOf<SMSMessage>()
    val cursor = context.contentResolver.query(
        Uri.parse("content://sms/$type"),
        null,
        null,
        null,
        null,
    )
    cursor?.use {
        val indexMessage = it.getColumnIndex("body")
        val indexSender = it.getColumnIndex("address")
        val indexDate = it.getColumnIndex("date")
        val indexRead = it.getColumnIndex("read")
        val indexType = it.getColumnIndex("type")
        val indexThread = it.getColumnIndex("thread_id")
        val indexService = it.getColumnIndex("service_center")
        while (it.moveToNext()) {
            messages.add(
                SMSMessage(
                    message = it.getString(indexMessage),
                    sender = it.getString(indexSender).removePrefix("+48"),
                    date = it.getLong(indexDate),
                    read = it.getString(indexRead).toBoolean(),
                    type = it.getInt(indexType),
                    thread = it.getInt(indexThread),
                    service = it.getString(indexService) ?: ""
                )
            )
        }
    }
    return messages
}