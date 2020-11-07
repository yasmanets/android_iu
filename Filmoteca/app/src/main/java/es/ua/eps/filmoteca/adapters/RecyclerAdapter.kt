package es.ua.eps.filmoteca.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.filmoteca.R
import es.ua.eps.filmoteca.film.Film

class RecyclerAdapter(private val films: ArrayList<Film>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var listener: (film: Film) -> Unit = {}
    private var longListener: (film: Film) -> Unit = {}
    var selectedFilms: ArrayList<Film> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            val position: Int = holder.adapterPosition
            listener(films[position])
        }
        view.setOnLongClickListener {
            val position: Int = holder.adapterPosition
            longListener(films[position])
            if (selectedFilms.contains(films[position])) {
                holder.itemView.alpha = 1.0f
                selectedFilms.remove(films[position])
            }
            else {
                holder.itemView.alpha = 0.3f
                selectedFilms.add(films[position])
            }
            return@setOnLongClickListener true
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(films[position])
    }

    override fun getItemCount(): Int {
        return films.size
    }

    fun setOnItemClickListener(listener: (film: Film) -> Unit) {
        this.listener = listener
    }

    fun setOnLongItemClickListener(longListener: (film: Film) -> Unit) {
        this.longListener = longListener
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var filmImage: ImageView = view.findViewById(R.id.listFilmImage)
        var filmTitle: TextView = view.findViewById(R.id.listFilmTitle)
        var filmDirector: TextView = view.findViewById(R.id.listFilmDirector)

        fun bind(film: Film) {
            filmImage.setImageResource(film.imageResId)
            filmTitle.text = film.title
            filmDirector.text = film.director
        }
    }
}