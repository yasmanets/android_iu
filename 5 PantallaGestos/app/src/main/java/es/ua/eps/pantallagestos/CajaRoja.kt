package es.ua.eps.pantallagestos

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat

class CajaRoja: View {

    var posX: Float? = null
    var posY: Float? = null
    var width: Float? = null
    var heigh: Float? = null
    var rect: RectF? = null
    private var punteroActivo = -1
    private var isClicked = false;
    private var isRed = true
    private var color: Int? = null

    var startX: Float? = null
    var startY: Float? = null
    var finishX: Float? = null
    var finishY: Float? = null

    var detector: GestureDetectorCompat? = null

    constructor(context: Context) : this(context, null) { init() }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) { init() }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) { init() }


    private fun init() {
        posX = 50f
        posY = 50f
        width = 150f
        heigh = 150f
        val listener = ListenerGestos()
        detector = GestureDetectorCompat(context, listener)
        detector?.setOnDoubleTapListener(listener)
        color = Color.RED
        startX = posX!!
        startY = posY!!
        finishX = posX!!
        finishY = posY!!
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.color = color!!
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 5F
        rect = RectF(posX!!, posY!!, width!!, heigh!!)

        canvas!!.drawRect(rect!!, paint)
        canvas!!.drawLine(startX!!, startY!!, finishX!!, finishY!!, paint)
    }

    private fun setCoordinates(x: Float, y: Float) {
        posX = x
        posY = y
        width = posX?.plus(100F)
        heigh = posY?.plus(100F)
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        detector?.onTouchEvent(event)
        return true

        /*val action = event!!.action and MotionEvent.ACTION_MASK
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
        return isClicked*/
    }

    inner class ListenerGestos: GestureDetector.SimpleOnGestureListener() {
        override fun onDown(event: MotionEvent?): Boolean {
            when (event!!.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    if (rect!!.contains(event!!.x, event!!.y)) {
                        punteroActivo = event!!.findPointerIndex(0)
                        setCoordinates(event!!.x, event!!.y)
                        isClicked = true
                    } else {
                        isClicked = false
                    }
                }
            }
            return isClicked
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            if (isClicked) {
                when (e2!!.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_MOVE -> {
                        val index = e2!!.findPointerIndex(punteroActivo)
                        setCoordinates(e2!!.getX(index), e2!!.getY(index))
                    }
                    MotionEvent.ACTION_POINTER_UP -> {
                        var indice = (e1!!.action and MotionEvent.ACTION_POINTER_INDEX_MASK shr MotionEvent.ACTION_POINTER_INDEX_SHIFT)
                        val idPuntero = e1!!.getPointerId(indice)

                        if (idPuntero == punteroActivo) {
                            val indiceNuevoPunteroActivo = if (indice == 0) 1 else 0
                            setCoordinates(e1!!.getX(indiceNuevoPunteroActivo), e1!!.getY(indiceNuevoPunteroActivo))
                            punteroActivo = e1!!.getPointerId(indiceNuevoPunteroActivo)
                        }
                    }
                }
            }
            return isClicked
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            if (isRed) {
                color = Color.BLUE
                isRed = false
            }
            else {
                color = Color.RED
                isRed = true
            }
            invalidate()
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            if (isClicked) {
                startX = e1!!.x
                startY = e1!!.y
                finishX = e2!!.x
                finishY = e2!!.y
            }
            invalidate()
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }
}