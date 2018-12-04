package com.example.aaron.popularmoviespractice.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Movie(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title:String,
    var image: String,
    var synopsis: String,
    var rating: String,
    var date: String
)