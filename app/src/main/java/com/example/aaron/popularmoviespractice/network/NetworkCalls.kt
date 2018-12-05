package com.example.aaron.popularmoviespractice.network

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.aaron.popularmoviespractice.R
import com.example.aaron.popularmoviespractice.data.Movie
import com.example.aaron.popularmoviespractice.objects.Globals
import org.json.JSONObject
import java.net.URI
import java.net.URL
import java.nio.channels.CompletionHandler

object NetworkCalls {
    fun getMoviesFromNetwork(context: Context, completionHandler: (response: Boolean) -> Unit) {
        var urlBuilder = Uri.Builder()
        if(Globals.chosen_filter == Globals.POPULAR_KEY_NUMBER) {
            urlBuilder.scheme("https").authority(context.getString(R.string.fetchMovies)).appendPath("3").appendPath("movie").appendPath(Globals.POPULAR_KEY)
        } else {
            urlBuilder.scheme("https").authority(context.getString(R.string.fetchMovies)).appendPath("3").appendPath("movie").appendPath(Globals.TOP_RATED_KEY)
        }
        urlBuilder.appendQueryParameter(Globals.API_KEY,Globals.API_KEY_VALUE)
        val url = urlBuilder.build()
        Log.e("urlbuilt",url.toString())

        /*if(Statics.chosenFilter == Statics.POPULARITY_FILTER)
            url = resources.getString(R.string.queryPopularity)+"&page=1"
        else
            url = resources.getString(R.string.queryRating)+"&page=1"*/

        val stringResquest = StringRequest(Request.Method.GET,url.toString(), Response.Listener {
                response ->
            val raiz = JSONObject(response)
            val results = raiz.getJSONArray("results")
            var i = 0
            if (results.length() > 0) {
                Thread {
                    Globals.moviesDB?.movieDao()?.let {
                        it.deleteAll()
                    }
                }.start()
            }
            while (i<results.length()) {
                val json: JSONObject = results.getJSONObject(i)
                val movieImage = json.optString("poster_path","")
                val mImageUrl = context.getString(R.string.imageUrl,movieImage)
                Log.e("imageUrl",mImageUrl)
                val movie = Movie(json.getString("id"),json.getString("original_title"),mImageUrl,json.getString("overview"),
                    json.getString("vote_average"),json.getString("release_date"),json.getString("popularity"),json.getString("original_language"))
                Globals.moviesDB?.movieDao()?.let {
                    movieDao ->
                    Thread {
                        movieDao.insertMovie(movie)
                    }.start()
                }
                i++
            }
            completionHandler(true)
            /*main_pgBar.visibility = View.GONE
            Statics.pages ++
            inflateFragment()*/
        }, Response.ErrorListener {
                error ->
            completionHandler(false)
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
            Log.e("TAG","NO SE PUDO",error)
        })
        stringResquest.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        Globals.volleyQue?.let {
            it.add(stringResquest)
        }
    }
    fun getTrailers(context: Context,movieId: String,completionHandler: (response: ArrayList<Trailer>) -> Unit) {
        val url = context.getString(R.string.trailers,movieId,Globals.API_KEY_VALUE)
        val stRequest = StringRequest(Request.Method.GET,url, Response.Listener {
                response ->
            val raiz = JSONObject(response)
            if(raiz.has("results")){
                val results = raiz.getJSONArray("results")
                if(results.length() > 0){
                    var i = 0
                    while (i<results.length()){
                        val json = results.getJSONObject(i)
                        val trailer = Trailer(json.getString("key"),json.getString("site"),json.getString("name"))
                        arrayVideos.add(trailer)
                        i++
                    }
                    mostrarTrailers()
                }else{
                    Toast.makeText(context,resources.getString(R.string.noTrailer),Toast.LENGTH_SHORT).show()
                }
            }
            pDialog.dismiss()
        }, Response.ErrorListener {
                error ->
            Log.e("tag","error videos",error)
            Toast.makeText(context,resources.getString(R.string.errorTrailer),Toast.LENGTH_SHORT).show()
            pDialog.dismiss()
        })
        stRequest.setRetryPolicy(DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))
        Statics.volleyqueue.add(stRequest)
    }
}