package com.example.library.repository

import android.util.Log
import com.example.library.model.Book
import com.example.library.retrofit.BookRetrofit
import com.example.library.retrofit.NetworkMapper
import com.example.library.room.BookDao
import com.example.library.room.CacheMapper
import com.example.library.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(
    private val bookDao: BookDao,
    private val bookRetrofit: BookRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getFirstBook(): List<Book> {
        val networkBooks = bookRetrofit.get()
        val books = networkMapper.mapFromEntityList(networkBooks.data)

        for (book in books) {
            bookDao.insert(cacheMapper.mapToEntity(book))
        }
        val cachedBooks = bookDao.get()
        Log.d("Database 1", "cachedBooks $cachedBooks}")

        return books
    }

    suspend fun getSecondBooks(): List<Book> {
        val networkBooks = bookRetrofit.getSecond()
        return networkMapper.mapFromEntityList(networkBooks.data)
    }

    suspend fun getThirdBooks(): List<Book> {
        val networkBooks = bookRetrofit.getThird()
        return networkMapper.mapFromEntityList(networkBooks.data)
    }

    suspend fun getForthBooks(): List<Book> {
        val networkBooks = bookRetrofit.getForth()
        return networkMapper.mapFromEntityList(networkBooks.data)
    }

    suspend fun getFifthBooks(): List<Book> {
        val networkBooks = bookRetrofit.getFifth()
        return networkMapper.mapFromEntityList(networkBooks.data)
    }

    suspend fun getBook(): Flow<DataState<List<Book>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkBooks = bookRetrofit.get()
            val books = networkMapper.mapFromEntityList(networkBooks.data)

            for (book in books) {
                bookDao.insert(cacheMapper.mapToEntity(book))
            }

            val cachedBooks = bookDao.get()
            //emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBooks)))
            Log.d("Database 1", "${bookDao.get()}")

            emit(DataState.Success(books))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
