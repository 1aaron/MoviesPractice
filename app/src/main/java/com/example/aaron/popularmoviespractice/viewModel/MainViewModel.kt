package com.example.aaron.popularmoviespractice.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.aaron.popularmoviespractice.data.Movie
import com.example.aaron.popularmoviespractice.objects.Globals

class MainViewModel : ViewModel() {
    fun getMovies(): LiveData<List<Movie>> {
        Globals.moviesDB?.movieDao()?.let {
            movieDao ->
            return movieDao.getMovies()
        }
    }
}
