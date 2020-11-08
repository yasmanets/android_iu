package es.ua.eps.notificationssnackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.editText)
        val button: Button = findViewById(R.id.button)
        textView = findViewById(R.id.taskList)

        button.setOnClickListener {
            var message = editText.text.toString()
            if (message.isEmpty()) {
                message = "Escribe un texto"
            }
            editText.setText("")
            textView!!.append("$message\n")
            setSnackBar(it)
        }
    }

    private fun setSnackBar(view: View) {
        Snackbar.make(view, "Tarea añadida", Snackbar.LENGTH_LONG).setAction("Deshacer") {
            val tasks: MutableList<String> = textView!!.text.split("\n") as MutableList<String>
            tasks.removeLast()
            tasks.removeLast()
            textView!!.text = ""
            tasks.forEach { task -> textView!!.append("$task\n") }
        }.show()
    }
}
