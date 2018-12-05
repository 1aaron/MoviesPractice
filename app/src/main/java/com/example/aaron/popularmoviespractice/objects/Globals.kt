package com.example.aaron.popularmoviespractice.objects

import android.Manifest
import com.android.volley.RequestQueue
import com.example.aaron.popularmoviespractice.data.MoviesDB

object Globals {
    var moviesDB: MoviesDB? = null
    const val TOP_RATED_KEY = "top_rated"
    const val TOP_RATED_KEY_NUMBER = 1
    const val POPULAR_KEY = "popular"
    const val POPULAR_KEY_NUMBER = 0
    var chosen_filter = TOP_RATED_KEY_NUMBER
    const val API_KEY = "api_key"
    const val API_KEY_VALUE = "9094aaf027b4290723ab174da288d4e6"
    var volleyQue: RequestQueue? = null
    val permission = arrayOf(Manifest.permission.INTERNET)
    const val PERMISSION_CHECK: Int = 100
}