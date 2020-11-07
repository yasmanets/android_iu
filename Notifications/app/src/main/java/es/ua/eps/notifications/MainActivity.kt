package es.ua.eps.notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.editText)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            var message = editText.text.toString()
            if (message.isEmpty()) {
                message = "Escribe un texto"
            }
            editText.setText("")
            setToast(message)
        }

    }

    private fun setToast(message: String) {
        val layout = layoutInflater.inflate(R.layout.toast_style, null)
        layout.findViewById<TextView>(R.id.message).text = message
        val toast = Toast(this@MainActivity)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL, 0 ,0)
        toast.show()
    }
}