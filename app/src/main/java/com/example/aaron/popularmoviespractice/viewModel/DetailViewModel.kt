package com.example.aaron.popularmoviespractice.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.databinding.BaseObservable
import android.util.Log
import com.example.aaron.popularmoviespractice.data.Trailer
import com.example.aaron.popularmoviespractice.network.NetworkCalls

class DetailViewModel : BaseObservable() {

    var movieTitle: String = "un título para probar"
    var movieYear: String = "2015"
    var movieLength: String = "2015"
    var movieRate: String = "2015"
    var movieDescription: String = "2015"
    var movieId: String = ""

    var trailers : MutableLiveData<ArrayList<Trailer>> = MutableLiveData()

    fun fetchTrailers(context: Context) {
        NetworkCalls.getTrailers(context,movieId) {
            if (it != null) {
                trailers.value = it
            }
        }
    }
}
