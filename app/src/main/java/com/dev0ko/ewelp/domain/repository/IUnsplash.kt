package com.dev0ko.ewelp.domain.repository

import androidx.lifecycle.LiveData
import com.dev0ko.ewelp.data.entity.Photo

interface IUnsplash {
    fun getData(search: String, perPage: Int, page: Int): LiveData<List<Photo>>
    fun getRandom() : LiveData<List<Photo>>
}