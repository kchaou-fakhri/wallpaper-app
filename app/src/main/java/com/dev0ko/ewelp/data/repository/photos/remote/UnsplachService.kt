package com.dev0ko.ewelp.data.repository.photos.remote




import com.dev0ko.ewelp.data.entity.Photo
import com.dev0ko.ewelp.data.entity.UnsplashResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface UnsplashService {
    @GET("search/photos/")
    fun getAllData(
        @Query("page") page: Int,
        @Query("per_page") pageLimit: Int,
        @Query("order_by") order: String,
        @Query("query") query: String,
        @Query("orientation") orientation : String
    ) :  Call<UnsplashResponse>

    @GET("photos")
    fun getRandom(


    ) :  Call<List<Photo>>
}