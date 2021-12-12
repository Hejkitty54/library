package com.example.library.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "author")
    var author: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "content")
    var content: String,

)
