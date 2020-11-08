package es.ua.eps.ejemplohilos

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ThreadActivity : AppCompatActivity() {

    var count: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        count = findViewById(R.id.threadTextView)
        val thread = ThreadCrono()
        thread.start()

        val back: Button = findViewById(R.id.threadBack)
        back.setOnClickListener { finish() }
    }

    inner class ThreadCrono(): Thread() {
        override fun run() {
            super.run()
            var t = 100
            do {
                runOnUiThread { count!!.text = "Contador: $t" }
                sleep(1000)
                t--
            } while (t > 0)
            runOnUiThread { count!!.text = "Contador terminado" }
        }
    }
}