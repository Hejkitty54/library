package com.example.library.retrofit

import com.example.library.model.Book
import com.example.library.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<BookNetworkEntity, Book> {
    override fun mapFromEntity(entity: BookNetworkEntity): Book {
        return Book(
            title = entity.title,
            author = entity.author,
            genre = entity.genre,
            content = entity.content
        )
    }

    override fun mapToEntity(domainModel: Book): BookNetworkEntity {
        return BookNetworkEntity(
            title = domainModel.title,
            author = domainModel.author,
            genre = domainModel.genre,
            content = domainModel.content
        )
    }

    fun mapFromEntityList(entities: List<BookNetworkEntity>): List<Book> {
        return entities.map { mapFromEntity(it) }
    }
}