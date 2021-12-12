package com.example.library.retrofit

import retrofit2.http.GET

interface BookRetrofit {

    @GET("texts?_quantity=1&_characters=300&_seed=1&_locale/")
    suspend fun get(): ResponseNetWorkEntity

    @GET("texts?_quantity=2&_characters=300&_seed=2&_locale")
    suspend fun getSecond(): ResponseNetWorkEntity

    @GET("texts?_quantity=3&_characters=300&_seed=3&_locale")
    suspend fun getThird(): ResponseNetWorkEntity

    @GET("texts?_quantity=4&_characters=300&_seed=4&_locale")
    suspend fun getForth(): ResponseNetWorkEntity

    @GET("texts?_quantity=5&_characters=300&_seed=5&_locale")
    suspend fun getFifth(): ResponseNetWorkEntity
}