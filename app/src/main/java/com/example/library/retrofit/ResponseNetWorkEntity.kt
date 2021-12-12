package com.example.library.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseNetWorkEntity(
    @SerializedName("status")
    @Expose
    var status: String,

    @SerializedName("code")
    @Expose
    var code: String,

    @SerializedName("total")
    @Expose
    var total: Int,

    @SerializedName("data")
    @Expose
    var data: List<BookNetworkEntity>

)
