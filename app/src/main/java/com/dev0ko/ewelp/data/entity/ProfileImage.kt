package com.dev0ko.ewelp.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class ProfileImage(
    @SerializedName("small")  val small: String,
    @SerializedName("medium")  val medium: String,
    @SerializedName("large")  val large: String)