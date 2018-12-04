package com.example.aaron.popularmoviespractice.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Movie(
    @PrimaryKey()
    var id: String,
    var title:String,
    var image: String,
    var synopsis: String,
    var rating: String,
    var date: String,
    var popularity: String,
    var language: String
)