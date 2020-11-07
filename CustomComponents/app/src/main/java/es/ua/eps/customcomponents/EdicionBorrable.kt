package es.ua.eps.customcomponents

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class EdicionBorrable: LinearLayout {

    var editText: EditText? = null
    var button: Button?= null

    constructor(ctx: Context?) : super(ctx) { init() }
    constructor(ctx: Context?, atts: AttributeSet?)
            : super(ctx, atts) { init() }

    private fun init() {
        var layout = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layout.inflate(R.layout.edicionborrable, this, true)
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        button!!.setOnClickListener {
            editText!!.setText("")
        }
    }
}