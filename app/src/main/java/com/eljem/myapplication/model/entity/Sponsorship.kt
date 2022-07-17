package com.eljem.myapplication.model.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Sponsorship(@SerializedName("tagline") val tagline: String)
