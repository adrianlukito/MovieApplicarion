package com.example.movieapplication.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.movieapplication.BaseApplication
import com.example.movieapplication.utils.Constants

class MarkAsFavoriteReceiver(private val refresh: () -> Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        refresh()
    }

    companion object {
        fun sendBroadcast() {
            val broadcastIntent = Intent().apply { action = Constants.MARK_FAVORITE_RECEIVED_ACTION }
            BaseApplication.instance.sendBroadcast(broadcastIntent)
        }
    }
}