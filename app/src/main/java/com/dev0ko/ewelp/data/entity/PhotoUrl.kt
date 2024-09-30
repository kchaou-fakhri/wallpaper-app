package com.dev0ko.ewelp.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoUrl( @SerializedName("full")  val full: String,
                     @SerializedName("regular")  val regular: String,
                        @SerializedName("small")  val small: String)