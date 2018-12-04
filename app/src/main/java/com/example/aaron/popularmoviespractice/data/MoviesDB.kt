package com.example.aaron.popularmoviespractice.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log

@Database(entities = arrayOf(Movie::class),version = 1,exportSchema = false)
abstract class MoviesDB: RoomDatabase() {
        companion object {
            var sInstance: MoviesDB? = null
            var LOG_TAG = MoviesDB::class.java.simpleName
            val LOCK = Object()
            const val DATABASE_NAME = "moviesDB"
            fun getInstance(context: Context): MoviesDB {
                if (sInstance == null) {
                    synchronized(LOCK) {
                        Log.d(LOG_TAG,"Creating Database instance")
                        sInstance = Room.databaseBuilder(context,MoviesDB::class.java, DATABASE_NAME)
                            .build()
                    }
                }
                Log.d(LOG_TAG,"GETTING THE DATABSE INSTANCE")
                return sInstance!!
            }
        }
    abstract fun movieDao(): MovieDao
}