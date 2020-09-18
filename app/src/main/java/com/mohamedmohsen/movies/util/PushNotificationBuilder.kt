package com.mohamedmohsen.movies.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.mohamedmohsen.movies.R

object PushNotificationBuilder {

    @RequiresApi(Build.VERSION_CODES.O)
    private fun notificationChannelConfig(context: Context) {
        val notificationChannel = NotificationChannel(context.getString(R.string.app_name), context.getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.description = context.getString(R.string.app_name)

        context.getSystemService(NotificationManager::class.java)?.createNotificationChannel(notificationChannel)
    }

    operator fun invoke(context: Context, title: String, text: String, pendingIntent: PendingIntent?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannelConfig(context)
        }

        NotificationManagerCompat.from(context).notify(
            1,
            NotificationCompat.Builder(context, context.getString(R.string.app_name))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(NotificationCompat.BigTextStyle())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()
        )
    }
}