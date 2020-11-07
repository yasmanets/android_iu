package es.ua.eps.filmoteca

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.*
import es.ua.eps.filmoteca.film.FilmDataSource

class FilmEditActivity : AppCompatActivity() {

    private var filmImage: ImageView? = null
    private var filmTitle: EditText? = null
    private var filmDirector: EditText? = null
    private var filmYear: EditText? = null
    private var filmIMDB: EditText? = null
    private var filmGender: Spinner? = null
    private var filmFormat: Spinner? = null
    private var filmComments: EditText? = null
    private var saveChanges: Button? = null
    private var cancelChanges: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_edit)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        getReferences()
        setViewContent(intent)

        saveChanges?.setOnClickListener {
            getUserValues(intent)
            setResult(Activity.RESULT_OK, null)
            finish()
        }
        cancelChanges?.setOnClickListener {
            setResult(Activity.RESULT_CANCELED, null)
            finish()
        }
    }

    private fun getReferences() {
        filmImage = findViewById(R.id.editFilmImage)
        filmTitle = findViewById(R.id.editFilmTitle)
        filmDirector = findViewById(R.id.editDirectorName)
        filmYear = findViewById(R.id.editFilmYear)
        filmIMDB = findViewById(R.id.editFilmIMDB)
        filmGender = findViewById(R.id.genderSpinner)
        filmFormat = findViewById(R.id.formatSpinner)
        filmComments = findViewById(R.id.editComments)
        saveChanges = findViewById(R.id.saveChanges)
        cancelChanges = findViewById(R.id.cancelChanges)
    }

    private fun setViewContent(intent: Intent) {
        val films = FilmDataSource.films
        val position = intent.getIntExtra(FILM_CLICKED, 0)
        filmImage?.setImageResource(films[position].imageResId)
        filmTitle?.text = Editable.Factory.getInstance().newEditable(films[position].title)
        filmDirector?.text = Editable.Factory.getInstance().newEditable(films[position].director)
        filmYear?.text = Editable.Factory.getInstance().newEditable(films[position].year.toString())
        filmIMDB?.text = Editable.Factory.getInstance().newEditable(films[position].imdbUrl)
        filmGender?.setSelection(films[position].genre)
        filmFormat?.setSelection(films[position].format)
        filmComments?.text = Editable.Factory.getInstance().newEditable(films[position].comments)
    }

    private fun getUserValues(intent: Intent) {
        val films = FilmDataSource.films
        val position = intent.getIntExtra(FILM_CLICKED, 0)
        films[position].title = filmTitle?.text.toString()
        films[position].director = filmDirector?.text.toString()
        films[position].year = (filmYear?.text.toString()).toInt()
        films[position].imdbUrl = filmIMDB?.text.toString()
        films[position].genre = filmGender?.selectedItemPosition ?: 0
        films[position].format = filmFormat?.selectedItemPosition ?: 0
        films[position].comments = filmComments?.text.toString()
    }
}