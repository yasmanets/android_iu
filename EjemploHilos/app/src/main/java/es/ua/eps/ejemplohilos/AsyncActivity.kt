package es.ua.eps.ejemplohilos

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AsyncActivity : AppCompatActivity() {

    var count: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

        count = findViewById(R.id.asyncTextView)
        AsyncCrono().execute()

        val back: Button = findViewById(R.id.asyncBack)
        back.setOnClickListener { finish() }
    }

    inner class AsyncCrono: AsyncTask<String, Int, String>() {
        override fun doInBackground(vararg params: String?): String {
            var t = 100
            do {
                runOnUiThread { count!!.text = "Contador: $t" }
                Thread.sleep(1000)
                t--
            } while (t > 0)
            runOnUiThread { count!!.text = "Contador terminado" }
            return ""
        }

    }
}