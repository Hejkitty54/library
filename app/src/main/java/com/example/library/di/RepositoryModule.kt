package com.example.library.di

import com.example.library.repository.MainRepository
import com.example.library.retrofit.BookRetrofit
import com.example.library.retrofit.NetworkMapper
import com.example.library.room.BookDao
import com.example.library.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        bookDao: BookDao,
        retrofit: BookRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(bookDao, retrofit, cacheMapper, networkMapper)
    }
}