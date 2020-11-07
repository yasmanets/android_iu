package es.ua.eps.filmoteca.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import es.ua.eps.filmoteca.R
import es.ua.eps.filmoteca.film.Film

class ListAdapter (private val context: Context, private val film: ArrayList<Film>): BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return film.count()
    }

    override fun getItem(position: Int): Film {
        return film[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.item_film, parent, false)
        val filmImage: ImageView = view.findViewById(R.id.listFilmImage)
        val filmTitle: TextView = view.findViewById(R.id.listFilmTitle)
        val filmDirector: TextView = view.findViewById(R.id.listFilmDirector)
        val film: Film = getItem(position)
        filmImage.setImageResource(film.imageResId)
        filmTitle.text = film.title
        filmDirector.text = film.director
        return view
    }


}