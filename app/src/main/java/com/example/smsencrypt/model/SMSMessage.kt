package com.example.smsencrypt.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class SMSMessage (
    val message: String,
    val sender: String,
    val date: Long,
    val read: Boolean,
    val type: Int,
    val thread: Int,
    val service: String)

fun Long.parseDate():String{
    val date = Date(this)
    val format = SimpleDateFormat("dd/MMM/yyyy HH:mm", Locale.getDefault())
    return format.format(date)
}
fun encrypt( mess :String):String{

    var cypher="qaz"
    for (ch in mess){
        cypher+=(ch+4)
    }
    return  cypher
}
fun decrypt(cypher: String):String{
    if(cypher.length<3){
        return  cypher
    }
    else{
         if(cypher.subSequence(0,3)=="qaz"){

            val c =cypher.removePrefix("qaz")
            var mess=""
            for (ch in c){
                mess+=(ch-4)
            }
             return mess
        } else{
             return cypher
        }
    }
}
//TODO model wszystkich wiadomości
//TODO viewmodel posortowanych wiadomości(jest już w MainScreen prawie gotowy)
//TODO wiewmodel rozmów(SMSMessage.thread)[ostatniawiadomość z danegej rozmowy]