package es.ua.eps.filmoteca

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.filmoteca.adapters.RecyclerAdapter
import es.ua.eps.filmoteca.film.Film
import es.ua.eps.filmoteca.film.FilmDataSource

const val FILM_CLICKED = "FILM_CLICKED"

class FilmListFragment : Fragment() {

    var recyclerView: RecyclerView? = null
    var adapter: RecyclerView.Adapter<*>? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var films: ArrayList<Film> = arrayListOf()
    var actionMode: Boolean? = null

    override fun onCreateView(inflater: LayoutInflater,
                                  container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_film_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        FilmDataSource.films.forEach { f -> films.add(f) }

        recyclerView = view.findViewById(R.id.filmList)
        recyclerView?.itemAnimator = DefaultItemAnimator()
        layoutManager = LinearLayoutManager(view.context)
        recyclerView?.layoutManager = layoutManager
        val adapter = RecyclerAdapter(films)
        recyclerView?.adapter = adapter
        this.adapter = adapter

        adapter.setOnItemClickListener { film ->
            val position = FilmDataSource.films.indexOf(film)
            var filmDataFragment = fragmentManager!!.findFragmentById(R.id.filmDataFragment) as FilmDataFragment?
            if (filmDataFragment != null) {
                filmDataFragment.setSelectedFilm(position)
            }
            else {
                Log.d("list", "else")
                filmDataFragment = FilmDataFragment()
                val args = Bundle()
                args.putInt(FILM_CLICKED, position)
                filmDataFragment.arguments = args

                val t = fragmentManager!!.beginTransaction()
                t.replace(R.id.fragment_container, filmDataFragment)
                t.addToBackStack(null)
                t.commit()
            }
        }
        adapter.setOnLongItemClickListener { film ->
            val actionModeCallback: ActionMode.Callback = object : ActionMode.Callback {
                override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                    return when (item.itemId) {
                        R.id.delete -> {
                            films.removeAll(adapter.selectedFilms)
                            adapter.notifyDataSetChanged()
                            mode.finish()
                            true
                        }
                        else -> false
                    }
                }

                override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                    val inflater = mode.menuInflater
                    inflater.inflate(R.menu.action_menu, menu)
                    return true
                }

                override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
                    return false
                }

                override fun onDestroyActionMode(p0: ActionMode?) {
                    actionMode = null
                }
            }

            if (actionMode != null) {
                false
            }
            else {
                this@FilmListFragment.view?.startActionMode(actionModeCallback)
                true
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addFilm -> {
                addFilm()
                adapter?.notifyDataSetChanged()
                return true
            }
            R.id.about -> {
                val intent = Intent(view?.context, AboutActivity::class.java)
                startActivity(intent)

            }
            else -> return false
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addFilm() {
        val f = Film()
        f.imageResId = R.drawable.intocable
        f.title = getText(R.string.intouchables).toString()
        f.director = "Olivier Nakache"
        f.comments = ""
        f.format = Film.FORMAT_DVD
        f.genre = Film.GENRE_COMEDY
        f.year = 2011
        f.imdbUrl = "https://www.imdb.com/title/tt1675434/?ref_=nv_sr_srsg_0"
        films.add(f)
        FilmDataSource.films.add(f)
    }
}