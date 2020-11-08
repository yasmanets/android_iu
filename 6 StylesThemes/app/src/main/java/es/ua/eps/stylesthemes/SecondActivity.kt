package es.ua.eps.stylesthemes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val back: Button = findViewById(R.id.back)
        back.setOnClickListener {
            finish()
        }
    }
}