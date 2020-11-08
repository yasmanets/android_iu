package es.ua.eps.filmoteca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (findViewById<View?>(R.id.fragment_container) != null) {
            if (savedInstanceState != null) return
            val filmListFragment = FilmListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, filmListFragment).commit()
        }
    }
}