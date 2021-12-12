package com.example.library.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookCacheEntity: BookCacheEntity): Long

    @Query("SELECT * FROM books")
    suspend fun get(): List<BookCacheEntity>
}