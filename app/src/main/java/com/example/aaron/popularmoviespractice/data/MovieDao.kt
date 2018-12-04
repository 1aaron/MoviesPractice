package com.example.aaron.popularmoviespractice.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface MovieDao {
    @Insert
    fun insertMovie(movie: Movie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie:Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("DELETE FROM Movie")
    fun deleteAll()

    @Query("SELECT * FROM Movie")
    fun getMovies(): LiveData<List<Movie>>
}