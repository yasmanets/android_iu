package es.ua.eps.dialogs

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val color: Button = findViewById(R.id.color)
        val size: Button = findViewById(R.id.size)
        val text: TextView = findViewById(R.id.text)

        color.setOnClickListener {
            val items = arrayOf("Blanco y Negro", "Negro y Blanco", "Negro y Verde")
            val dialog = AlertDialog.Builder(this)
                .setTitle("Elige la combinación de colores")
                .setItems(items) { dialog, which ->
                    val selected: String = items[which]
                    when (selected) {
                        "Blanco y Negro" -> {
                            text.setBackgroundColor(Color.WHITE)
                            text.setTextColor(Color.BLACK)
                        }
                        "Negro y Blanco" -> {
                            text.setBackgroundColor(Color.BLACK)
                            text.setTextColor(Color.WHITE)
                        }
                        "Negro y Verde" -> {
                            text.setBackgroundColor(Color.BLACK)
                            text.setTextColor(Color.GREEN)
                        }
                    }
                }
                .setNegativeButton("Cancelar", null)
                .create()
            dialog.show()
        }

        size.setOnClickListener {
            val items = arrayOf("Pequeño", "Mediano", "Grande")
            val dialog = AlertDialog.Builder(this)
                .setTitle("Elige el tamaño del texto")
                .setItems(items) { dialog, which ->
                    val selected: String = items[which]
                    when (selected) {
                        "Pequeño" -> text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8F)
                        "Mediano" -> text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                        "Grande" -> text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)

                    }
                }
                .setNegativeButton("Cancelar", null)
                .create()
            dialog.show()
        }
    }
}