package com.example.library.model

data class Response(
    var status: String,
    var code: String,
    var total: Int,
    var data: Book
)
