package es.ua.eps.ejemplohilos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val thread: Button = findViewById(R.id.threadBtn)
        val async: Button = findViewById(R.id.asyncBtn)
        val coroutine: Button = findViewById(R.id.coroutineBtn)

        thread.setOnClickListener {
            val intent = Intent(this, ThreadActivity::class.java)
            startActivity(intent)
        }
        async.setOnClickListener {
            val intent = Intent(this, AsyncActivity::class.java)
            startActivity(intent)
        }
        coroutine.setOnClickListener {
            val intent = Intent(this, CoroutinesActivity::class.java)
            startActivity(intent)
        }
    }
}