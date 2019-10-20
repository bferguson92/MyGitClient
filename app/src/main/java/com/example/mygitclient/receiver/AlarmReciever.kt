package com.example.mygitclient.receiver

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.mygitclient.R
import com.example.mygitclient.util.Constants
import com.example.mygitclient.view.MainActivity

class AlarmReciever : BroadcastReceiver() {

    private lateinit var notificationManager: NotificationManager

    override fun onReceive(context: Context?, intent: Intent?) {
        notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, createNotification(context))
    }

    private fun createNotification(context: Context?): Notification {
        var notification: Notification? = null
        val pendingIntent = PendingIntent.getActivity(
            context,
            Constants.NOTIFICATION_REQUEST_CODE,
            Intent(context, MainActivity::class.java),
            0
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(
                    "channel",
                    "alarm_channel",
                    NotificationManager.IMPORTANCE_LOW
                )



            notificationManager.createNotificationChannel(notificationChannel)




            notification = Notification.Builder(context, "channel")
                .setSmallIcon(R.drawable.ic_access_alarm_black_24dp)
                .setContentTitle("Alarm")
                .setContentText("Time to check git!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()
        } else {
            notification = Notification.Builder(context)
                .setSmallIcon(R.drawable.ic_access_alarm_black_24dp)
                .setContentTitle("Alarm")
                .setContentText("Time to check git!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_LOW)
                .build()
        }
        return notification
    }
}