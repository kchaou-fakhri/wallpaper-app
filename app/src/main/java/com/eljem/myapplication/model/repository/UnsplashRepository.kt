package com.eljem.myapplication.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eljem.myapplication.model.entity.Photo
import com.eljem.myapplication.model.entity.UnsplashResponse
import com.eljem.myapplication.model.repository.remote.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UnsplashRepository {


  fun getData(search: String, page: Int) : LiveData<List<Photo>>{
     var sort : String = "popular"
      var mutableLiveData = MutableLiveData<List<Photo>> ()

      val getPost = Retrofit.api.getAllData(1,page,sort,search, "portrait")

        getPost?.clone()?.enqueue(object :  Callback<UnsplashResponse> {

            override fun onResponse(

                call: Call<UnsplashResponse>,
                response: Response<UnsplashResponse>
            ) {
                Log.println(Log.ASSERT, "", response.message())
                if (response.isSuccessful){

                    mutableLiveData.value = response.body()?.results
//                   response.body()?.results?.forEach{
//                       Log.println(Log.ASSERT, "", it.toString())
//                   }
                }
            }

            override fun onFailure(call: Call<UnsplashResponse> , t: Throwable) {
                Log.println(Log.ASSERT, "", t.message.toString())

            }



        })

      return mutableLiveData
    }


    fun getRandom(count: Int) : LiveData<List<Photo>>{
        var sort : String = "popular"
        var mutableLiveData = MutableLiveData<List<Photo>> ()

        val getPost = Retrofit.api.getRandom(count)

        getPost?.clone()?.enqueue(object :  Callback<List<Photo>> {

            override fun onResponse(

                call: Call<List<Photo>>,
                response: Response<List<Photo>>
            ) {
                Log.println(Log.ASSERT, "", response.message())
                if (response.isSuccessful){

                    mutableLiveData.value = response.body()
//                   response.body()?.results?.forEach{
//                       Log.println(Log.ASSERT, "", it.toString())
//                   }
                }
            }

            override fun onFailure(call: Call<List<Photo>> , t: Throwable) {
                Log.println(Log.ASSERT, "", t.message.toString())

            }



        })

        return mutableLiveData
    }


}


