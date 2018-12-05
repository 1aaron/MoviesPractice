package com.example.aaron.popularmoviespractice

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.android.volley.toolbox.Volley
import com.example.aaron.popularmoviespractice.data.MoviesDB
import com.example.aaron.popularmoviespractice.network.NetworkCalls
import com.example.aaron.popularmoviespractice.objects.Globals
import com.example.aaron.popularmoviespractice.ui.view.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        Globals.moviesDB = MoviesDB.getInstance(this)
        Globals.volleyQue = Volley.newRequestQueue(this)
        ActivityCompat.requestPermissions(this,Globals.permission,Globals.PERMISSION_CHECK)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.options_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.itemId?.let {
            id ->
            val filters = arrayOf(resources.getString(R.string.filterPopularity),resources.getString(R.string.filterRated))
            if(id == R.id.menu_filter){
                val builder = AlertDialog.Builder(this)
                    .setSingleChoiceItems(filters,Globals.chosen_filter) { _, i ->
                        Globals.chosen_filter = i
                    }
                    .setPositiveButton("Ok") { _, _ ->
                        NetworkCalls.getMoviesFromNetwork(this) {}
                    }
                    .create()
                builder.setTitle(resources.getString(R.string.filter))
                builder.show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
