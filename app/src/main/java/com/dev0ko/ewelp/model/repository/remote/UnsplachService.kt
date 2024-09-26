package com.dev0ko.ewelp.model.repository.remote




import com.dev0ko.ewelp.model.entity.Photo
import com.dev0ko.ewelp.model.entity.UnsplashResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface UnsplachService {
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