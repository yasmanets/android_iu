package es.ua.eps.ejemplohilos

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CoroutinesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        val count: TextView = findViewById(R.id.coroutineTextView)
        val job = GlobalScope.launch(Dispatchers.IO) {
            var t = 100
            do {
                launch(Dispatchers.Main) {
                    count.text = "Contador: $t"
                }
                Thread.sleep(1000)
                t--
            } while (t > 0)
            launch(Dispatchers.Main) {
                    count.text = "Contador terminado"
            }
        }

        val back: Button = findViewById(R.id.coroutineBack)
        back.setOnClickListener { finish() }

    }
}