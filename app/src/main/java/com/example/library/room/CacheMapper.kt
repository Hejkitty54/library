package com.example.library.room

import com.example.library.model.Book
import com.example.library.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<BookCacheEntity, Book> {
    override fun mapFromEntity(entity: BookCacheEntity): Book {
        return Book(
            title = entity.title,
            author = entity.author,
            genre = entity.genre,
            content = entity.content
        )
    }

    override fun mapToEntity(domainModel: Book): BookCacheEntity {
        return BookCacheEntity(
            title = domainModel.title,
            author = domainModel.author,
            genre = domainModel.genre,
            content = domainModel.content
        )
    }

    fun mapFromEntityList(entities: List<BookCacheEntity>): List<Book> {
        return entities.map { mapFromEntity(it) }
    }

}