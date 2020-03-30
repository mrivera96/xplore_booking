package com.xplorerentacar.xplorereservas.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.xplorerentacar.xplorereservas.MainActivity
import com.xplorerentacar.xplorereservas.R
import java.io.IOException
import kotlin.random.Random

class MyFirebaseMessagingService: FirebaseMessagingService() {
    val TAG = "FCM Service"
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.notification != null){
            val titulo = remoteMessage.notification!!.title
            val detalle = remoteMessage.notification!!.body


            notificacion(titulo, detalle);

        }

    }

    private fun notificacion(titulo: String?, detalle: String?){
        val id = "mensaje"
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(this, id)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val nc = NotificationChannel(id,"nuevo", NotificationManager.IMPORTANCE_HIGH)
            nc.setShowBadge(true)
            nm.createNotificationChannel(nc)
        }

        try {
            builder.setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(titulo)
                    .setContentText(detalle)
                    .setContentIntent(clickNotif())
                    .setStyle(NotificationCompat.BigTextStyle())
                    .setSmallIcon(R.mipmap.ic_launcher_round)

            val random : Random = Random(0)
            val notirandom = random.nextInt(8000)

            nm.notify(notirandom, builder.build())

        }catch (e: IOException){
            e.printStackTrace()
        }
    }

    fun clickNotif() : PendingIntent{
        val nf = Intent(applicationContext, MainActivity::class.java)
        nf.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        return PendingIntent.getActivity(this, 0,nf, 0)
    }



}