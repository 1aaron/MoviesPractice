package com.example.aaron.popularmoviespractice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.android.volley.toolbox.Volley
import com.example.aaron.popularmoviespractice.data.MoviesDB
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

}
