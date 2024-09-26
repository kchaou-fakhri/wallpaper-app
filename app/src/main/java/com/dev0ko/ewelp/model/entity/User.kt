package com.dev0ko.ewelp.model.entity

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("id")  val id: String,
                @SerializedName("username")  val username: String,
                @SerializedName("name") val name : String,
                @SerializedName("profile_image")  val profileImage : ProfileImage,
                @SerializedName("total_photos")  val TotalPhotos: String,
                @SerializedName("total_collections")  val collection: String)