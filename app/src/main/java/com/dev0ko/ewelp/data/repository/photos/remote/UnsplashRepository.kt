package com.dev0ko.ewelp.data.repository.photos.remote

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev0ko.ewelp.data.entity.Photo
import com.dev0ko.ewelp.data.entity.UnsplashResponse
import com.dev0ko.ewelp.domain.repository.IUnsplash
import com.dev0ko.ewelp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class UnsplashRepository @Inject constructor(private val api : UnsplashService  ) : IUnsplash {


    @SuppressLint("SuspiciousIndentation")
    override fun getData(
        search: String,
        perPage: Int,
        page: Int,
        orientation: String
    ): LiveData<List<Photo>> {

        var mutableLiveData = MutableLiveData<List<Photo>>()

        val getPost = api.getAllData(page, perPage, Constants.POPULAR, search, orientation)

        getPost?.clone()?.enqueue(object : Callback<UnsplashResponse> {

            override fun onResponse(

                call: Call<UnsplashResponse>,
                response: Response<UnsplashResponse>
            ) {
                if (response.isSuccessful) {

                    mutableLiveData.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<UnsplashResponse> , t: Throwable) {
                Log.e("error", t.message.toString())

            }


        })

        return mutableLiveData
    }


   override fun getRandom() : LiveData<List<Photo>>{
        var mutableLiveData = MutableLiveData<List<Photo>> ()

        val getPost = api.getRandom()

        getPost?.clone()?.enqueue(object :  Callback<List<Photo>> {

            override fun onResponse(

                call: Call<List<Photo>>,
                response: Response<List<Photo>>
            ) {
                if (response.isSuccessful){

                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Photo>> , t: Throwable) {
                Log.e("error ", t.message.toString())

            }



        })

        return mutableLiveData
    }


}


