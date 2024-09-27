package com.dev0ko.ewelp.data.repository.photos.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev0ko.ewelp.data.entity.Photo
import com.dev0ko.ewelp.data.entity.UnsplashResponse
import com.dev0ko.ewelp.domain.repository.IUnsplash
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class UnsplashRepository @Inject constructor(private val api : UnsplashService  ) : IUnsplash {


 override  fun getData(search: String, perPage: Int, page: Int) : LiveData<List<Photo>>{
     var sort : String = "popular"
      var mutableLiveData = MutableLiveData<List<Photo>> ()

      val getPost = api.getAllData(page,perPage,sort,search, "portrait")

        getPost?.clone()?.enqueue(object :  Callback<UnsplashResponse> {

            override fun onResponse(

                call: Call<UnsplashResponse>,
                response: Response<UnsplashResponse>
            ) {
                Log.println(Log.ASSERT, "Data", response.message())
                if (response.isSuccessful){

                    mutableLiveData.value = response.body()?.results
                   response.body()?.results?.forEach{
                       Log.println(Log.ASSERT, "", it.toString())
                   }
                }
            }

            override fun onFailure(call: Call<UnsplashResponse> , t: Throwable) {
                Log.println(Log.ASSERT, "", t.message.toString())

            }



        })

      return mutableLiveData
    }


   override fun getRandom() : LiveData<List<Photo>>{
        var sort : String = "popular"
        var mutableLiveData = MutableLiveData<List<Photo>> ()

        val getPost = api.getRandom()

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


