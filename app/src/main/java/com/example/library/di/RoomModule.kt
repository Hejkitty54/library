package com.example.library.di

import android.content.Context
import androidx.room.Room
import com.example.library.room.BookDao
import com.example.library.room.BookDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideBookDb(@ApplicationContext context: Context): BookDataBase {
        return Room.databaseBuilder(
            context,
            BookDataBase::class.java,
            BookDataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBookDAO(bookDataBase: BookDataBase): BookDao {
        return bookDataBase.bookDao()
    }
}