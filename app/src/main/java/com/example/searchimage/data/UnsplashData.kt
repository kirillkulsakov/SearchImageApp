package com.example.searchimage.data

import com.google.gson.annotations.SerializedName

data class UnsplashData (
    @SerializedName("results")
    val result: ArrayList<DataList>
    )

class DataList(
    @SerializedName("description")
    val description: String

//    @SerializedName("urls")
//    val url: ArrayList<DataUrls>
)

/*data class DataUrls(

    @SerializedName("regular")
    val regular: String,

    @SerializedName("full")
    val full: String
)*/