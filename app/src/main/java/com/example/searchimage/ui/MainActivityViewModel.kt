package com.example.searchimage.ui

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.searchimage.MainActivity
import com.example.searchimage.api.RetroInstance
import com.example.searchimage.api.RetroService
import com.example.searchimage.data.DataList
import com.example.searchimage.data.UnsplashData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {


    var photoListData: MutableLiveData<UnsplashData> = MutableLiveData()

    fun getPhotoListDataObserver(): MutableLiveData<UnsplashData> {
        return photoListData
    }

    fun makeApiCall(input: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFormAPI(input)

        call.enqueue(object: Callback<UnsplashData> {
            override fun onResponse(
                call: Call<UnsplashData>,
                response: Response<UnsplashData>
            ) {
                if (response.isSuccessful) {
                    photoListData.postValue(response.body())}

            }

            override fun onFailure(call: Call<UnsplashData>, t: Throwable) {

            }


        })

    }
}


