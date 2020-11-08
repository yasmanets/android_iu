package es.ua.eps.pantallagestos

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.nio.channels.FileLock

class CajaRoja: View {

    var posX: Float? = null
    var posY: Float? = null
    var width: Float? = null
    var heigh: Float? = null
    var rect: RectF? = null
    private var punteroActivo = -1
    private var isClicked = false;

    constructor(context: Context) : this(context, null) { init() }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) { init() }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) { init() }


    private fun init() {
        posX = 50f
        posY = 50f
        width = 150f
        heigh = 150f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        rect = RectF(posX!!, posY!!, width!!, heigh!!)
        canvas!!.drawRect(rect!!, paint);
    }

    private fun setCoordinates(x: Float, y: Float) {
        posX = x
        posY = y
        width = posX?.plus(100F)
        heigh = posY?.plus(100F)
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event!!.action and MotionEvent.ACTION_MASK
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                if (rect!!.contains(event!!.x, event!!.y)) {
                    punteroActivo = event!!.findPointerIndex(0)
                    setCoordinates(event!!.x, event!!.y)
                    isClicked = true
                }
                else {
                    isClicked = false
                }

            }
            MotionEvent.ACTION_MOVE -> {
                val index = event.findPointerIndex(punteroActivo)
                setCoordinates(event!!.getX(index), event!!.getY(index))
            }
            MotionEvent.ACTION_POINTER_UP -> {
                var indice = (event.action and MotionEvent.ACTION_POINTER_INDEX_MASK shr MotionEvent.ACTION_POINTER_INDEX_SHIFT)
                val idPuntero = event.getPointerId(indice)

                if (idPuntero == punteroActivo) {
                    val indiceNuevoPunteroActivo = if (indice == 0) 1 else 0
                    setCoordinates(event!!.getX(indiceNuevoPunteroActivo), event!!.getY(indiceNuevoPunteroActivo))
                    punteroActivo = event.getPointerId(indiceNuevoPunteroActivo)
                }
            }
            MotionEvent.ACTION_UP -> punteroActivo = -1
            MotionEvent.ACTION_CANCEL -> punteroActivo = -1
        }
        return isClicked
    }
}