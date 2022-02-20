package com.example.searchimage.ui

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchimage.api.RetroInstance
import com.example.searchimage.api.RetroService
import com.example.searchimage.data.DataList
import com.example.searchimage.data.UnsplashData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    lateinit var photoListData: MutableLiveData<UnsplashData>

    init {
        photoListData = MutableLiveData()
    }

    fun getPhotoListDataObserver(): MutableLiveData<UnsplashData>{
        return photoListData
    }

    fun makeApiCall(input: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFormAPI(input)

        call.enqueue(object: Callback<List<UnsplashData>> {
            override fun onResponse(
                call: Call<List<UnsplashData>>,
                response: Response<List<UnsplashData>>
            ) {
                if (response.isSuccessful) {
                    photoListData.postValue(response?.body()!!.first())
                } else {
                    //  photoListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<UnsplashData>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })

    }
}


