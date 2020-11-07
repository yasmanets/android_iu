package es.ua.eps.notificationbar

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID = "Notification"
    private val NOTIFICATION_ID = 1
    var builder: NotificationCompat.Builder? = null
    var notificationManager: NotificationManagerCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tasksCount = 0
        val start: Button = findViewById(R.id.start)
        val stop: Button = findViewById(R.id.stop)

        start.setOnClickListener {
            tasksCount++
            if (tasksCount == 1) {
                createNotificationChannel()
                val intent = Intent(this, MainActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

                builder = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.robot)
                    .setContentTitle("Tareas en ejecucuón")
                    .setContentText("Tareas iniciadas: $tasksCount")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)

                notificationManager = NotificationManagerCompat.from(this)
                notificationManager!!.notify(NOTIFICATION_ID, builder!!.build())
            }
            else if (tasksCount > 1) {
                builder!!.setContentText("Tareas iniciadas $tasksCount")
                notificationManager!!.notify(NOTIFICATION_ID, builder!!.build())
            }
        }

        stop.setOnClickListener {
            tasksCount--
            if (tasksCount > 0) {
                builder!!.setContentText("Tareas iniciadas $tasksCount")
                notificationManager!!.notify(NOTIFICATION_ID, builder!!.build())
            }
            else if (tasksCount == 0) {
                notificationManager!!.cancel(NOTIFICATION_ID)
            }

        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Android Avanzado"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = "Notificacion de la barra de estado"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}