package es.ua.eps.filmoteca.film

import es.ua.eps.filmoteca.R

object FilmDataSource {
    val films: MutableList<Film> = mutableListOf()

    init {
        var f = Film()
        f.title = "El Señor de los Anillos: L Comunidad del Anillo"
        f.director = "Peter Jackson"
        f.imageResId = R.drawable.lord_one
        f.comments = ""
        f.format = Film.FORMAT_BLURAY
        f.genre = Film.GENRE_ACTION
        f.imdbUrl = "https://www.imdb.com/title/tt0120737/?ref_=nv_sr_srsg_0"
        f.year = 2001
        films.add(f)

        f = Film()
        f.title = "El Hobbit: Un viaje inesperado"
        f.director = "Peter Jackson"
        f.imageResId = R.drawable.hobbit_one
        f.comments = ""
        f.format = Film.FORMAT_BLURAY
        f.genre = Film.GENRE_ACTION
        f.imdbUrl = "https://www.imdb.com/title/tt0903624/?ref_=nv_sr_srsg_0"
        f.year = 2012
        films.add(f)

        f = Film()
        f.title = "Celda 211"
        f.director = "Daniel Monzón"
        f.imageResId = R.drawable.celda
        f.comments = ""
        f.format = Film.FORMAT_DVD
        f.genre = Film.GENRE_DRAMA
        f.imdbUrl = "https://www.imdb.com/title/tt1242422/?ref_=nv_sr_srsg_0"
        f.year = 2009
        films.add(f)

        f = Film()
        f.title = "La Vida de Nadie"
        f.director = "Ediuard Cortés"
        f.imageResId = R.drawable.vida_nadie
        f.comments = ""
        f.format = Film.FORMAT_DIGITAL
        f.genre = Film.GENRE_DRAMA
        f.imdbUrl = "https://www.imdb.com/title/tt0339862/?ref_=fn_al_tt_1"
        f.year = 2002
        films.add(f)

        f = Film()
        f.title = "Cien años de perdón"
        f.director = "Daniel Calparsoro"
        f.imageResId = R.drawable.perdon
        f.comments = ""
        f.format = Film.FORMAT_DVD
        f.genre = Film.GENRE_ACTION
        f.imdbUrl = "https://www.imdb.com/title/tt3655414/?ref_=fn_al_tt_3"
        f.year = 2016
        films.add(f)
    }
}