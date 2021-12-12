package com.example.library.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BookNetworkEntity(

    @SerializedName("title")
    @Expose
    var title: String,


    @SerializedName("author")
    @Expose
    var author: String,


    @SerializedName("genre")
    @Expose
    var genre: String,


    @SerializedName("content")
    @Expose
    var content: String

)
