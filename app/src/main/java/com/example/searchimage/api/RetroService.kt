package com.example.searchimage.api

import android.provider.ContactsContract
import android.util.Log
import com.example.searchimage.BuildConfig
import com.example.searchimage.data.UnsplashData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val ACCESS_KEY = "bd3I4tZGQlOvQeWKYiBwPCS2BkKnwJnLDn-WTpM2fjA"
interface RetroService {

    @Headers("Accept-Version: v1", "Authorization: Client-ID $ACCESS_KEY")
    @GET("/search/photos")
    fun getDataFormAPI(
        @Query("query") query:String ,
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 20
        ):Call<UnsplashData>

}