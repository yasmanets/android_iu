package es.ua.eps.customcomponents

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView

class CustomTextView: AppCompatTextView {
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int)
            : super(context!!, attrs, defStyle) { init() }
    constructor(context: Context?) : super(context!!) { init() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) { init() }

    private fun init () {
        this.text = selectQuote()
        this.textSize = 20F
        this.gravity = 1
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.text = selectQuote()
        return super.onTouchEvent(event)
    }

    private fun selectQuote(): String {
        val randomNumber = (0..PopularQuotes.quotes.size).random()
        return PopularQuotes.quotes[randomNumber]
    }
}

object PopularQuotes {
    val quotes: MutableList<String> = mutableListOf();
    init {
        quotes.add("La innovación distingue a los líderes de los seguidores (Steve Jobs)")
        quotes.add("Muchas veces la gente no sabe lo que quiere hasta que se lo enseñas (Steve Jobs)")
        quotes.add("La mitad de lo que separa a los emprendedores exitosos de los que no triunfan es la perseverancia (Steve Jobs)")
        quotes.add("Lo peor que hacen los malos es obligarnos a dudar de los buenos (Jacinto Benavente)")
        quotes.add("Las guerras seguirán mientras el color de la piel siga siendo más importante que el de los ojos (Bob Marley)")
        quotes.add("Cada día sabemos más y entendemos menos (Albert Einstein)")
        quotes.add("Ningún hombre es lo bastante bueno para gobernar a otros sin su consentimiento. (Abraham Lincoln)")
        quotes.add("Todo lo que se come sin necesidad se roba al estómago de los pobres (Mahatma Gandhi)")
        quotes.add("Hay dos cosas que son infinitas: el universo y la estupidez humana; de la primera no estoy muy seguro (Albert Einstein)")
        quotes.add("El mundo es bello, pero tiene un defecto llamado hombre (Friedrich Nietzsche)")
        quotes.add("Pienso, luego existo (René Descartes)")
        quotes.add("La esperanza es un estimulante vital muy superior a la suerte (Friedrich Nietzsche)")
        quotes.add("Sólo puede ser feliz siempre el que sepa ser feliz con todo (Confucio)")
        quotes.add("De humanos es errar y de necios permanecer en el error (Marco Tulio Cicerón)")
        quotes.add("La verdadera sabiduría está en reconocer la propia ignorancia (Sócrates)")
        quotes.add("Hace falta toda una vida para aprender a vivir (Séneca)")
        quotes.add("Los verdaderos líderes deben estar dispuestos a sacrificarlo todo por la libertad de su pueblo (Nelson Mandela)")
        quotes.add("Aquel que más posee, más miedo tiene de perderlo (Leonardo Da Vinci)")
        quotes.add("Ojo por ojo y el mundo acabará ciego (Mahatma Gandhi)")
        quotes.add("Solo sé que no sé nada (Sócrates)")


    }
}