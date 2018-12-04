package com.example.aaron.popularmoviespractice.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.aaron.popularmoviespractice.R
import com.example.aaron.popularmoviespractice.data.Movie
import com.squareup.picasso.Picasso

class MoviesAdapter(var context:Context): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    var listMovies:List<Movie> = listOf()
    fun setData(movies: List<Movie>) {
        this.listMovies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.movie_image,p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovies[position]
        val imagePath = movie.image
        Log.e("imageToLoad",imagePath)

        val picasso = Picasso.get()
        picasso.isLoggingEnabled = true
        picasso.load(imagePath)
            .error(android.R.drawable.stat_notify_error)
            .fit()
            .into(holder.imageView)
        holder.imageView.setOnClickListener {
            Log.e("TAG","clicked image $position")
            //todo: show information
        }
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_movieImage)
    }
}