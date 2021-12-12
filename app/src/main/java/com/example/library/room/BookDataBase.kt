package com.example.library.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookCacheEntity::class], version = 1)
abstract class BookDataBase: RoomDatabase(){

    abstract fun bookDao(): BookDao

    companion object{
        val DATABASE_NAME: String = "book_db"
    }
}