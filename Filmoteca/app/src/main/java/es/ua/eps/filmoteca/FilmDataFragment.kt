package es.ua.eps.filmoteca

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import es.ua.eps.filmoteca.film.FilmDataSource

private const val EDIT_ACTIVITY_CODE = 1

class FilmDataFragment : Fragment() {

    private var filmImage: ImageView? = null
    private var filmTitle: TextView? = null
    private var directorName: TextView? = null
    private var filmYear: TextView? = null
    private var filmType: TextView? = null
    private var showIMDB: Button? = null
    private var editMovie: Button? = null
    private var goToMain: Button? = null
    private var imdbLink: String? = ""
    private var filmComments: TextView? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_film_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)

        val position = arguments?.getInt(FILM_CLICKED)
        val intent = activity?.intent
        getReferences(view)
        if (position != null) {
            setSelectedFilm(position)
        }

        editMovie?.setOnClickListener {
            val editIntent = Intent(view.context, FilmEditActivity::class.java)
            editIntent.putExtra(FILM_CLICKED, intent?.getIntExtra(FILM_CLICKED, 0))
            startActivityForResult(editIntent, EDIT_ACTIVITY_CODE)
        }
        goToMain?.setOnClickListener {
            val mainIntent = Intent(view.context, MainActivity::class.java)
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(mainIntent)
        }
        showIMDB?.setOnClickListener {
            val imdbIntent = Intent(Intent.ACTION_VIEW, Uri.parse(imdbLink))
            if (imdbIntent.resolveActivity(activity?.packageManager!!) != null) {
                startActivity(imdbIntent)
            }
        }
    }

    private fun getReferences(view: View) {
        filmImage = view.findViewById(R.id.filmImage)
        filmTitle = view.findViewById(R.id.filmTitle)
        directorName = view.findViewById(R.id.directorName)
        filmYear = view.findViewById(R.id.year)
        filmType = view.findViewById(R.id.filmType)
        filmComments = view.findViewById(R.id.comments)
        showIMDB = view.findViewById(R.id.showIMDB)
        editMovie = view.findViewById(R.id.editFilm)
        goToMain = view.findViewById(R.id.goMain)
    }

    private fun setViewContent(intent: Intent) {
        val films = FilmDataSource.films
        val position = intent.getIntExtra(FILM_CLICKED, 0)
        filmImage?.setImageResource(films[position].imageResId)
        filmTitle?.text = films[position].title
        directorName?.text = films[position].director
        filmYear?.text = films[position].year.toString()
        filmType?.text = String.format(getType(films[position].format) + ", " + getGender(films[position].genre))
        imdbLink = films[position].imdbUrl
        filmComments?.text = films[position].comments
    }

    private fun getType(position: Int): String {
        return when (position) {
            0 -> "DVD"
            1 -> "Bluray"
            2 -> "Digital"
            else -> ""
        }
    }

    private fun getGender(position: Int): String {
        return when (position) {
            0 -> getText(R.string.action).toString()
            1 -> getText(R.string.comedy).toString()
            2 -> getText(R.string.drama).toString()
            3 -> getText(R.string.scifi).toString()
            4 -> getText(R.string.terror).toString()
            else -> ""
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            Activity.RESULT_OK -> {
                setViewContent(activity?.intent!!)
                Toast.makeText(view?.context, getString(R.string.edited, filmTitle?.text), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    fun setSelectedFilm(position: Int) {
        val films = FilmDataSource.films
        filmImage?.setImageResource(films[position].imageResId)
        filmTitle?.text = films[position].title
        directorName?.text = films[position].director
        filmYear?.text = films[position].year.toString()
        filmType?.text = String.format(getType(films[position].format) + ", " + getGender(films[position].genre))
        imdbLink = films[position].imdbUrl
        filmComments?.text = films[position].comments
    }
}