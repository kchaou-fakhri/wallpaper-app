package com.eljem.myapplication.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eljem.myapplication.model.entity.Photo
import com.eljem.myapplication.model.repository.UnsplashRepository

class PhotoVM : ViewModel() {

    var unsplashRepository : UnsplashRepository

    init {
        unsplashRepository = UnsplashRepository()
    }

    fun getData(search: String, page: Int): LiveData<List<Photo>> {
        return unsplashRepository.getData(search, page)
    }

    fun getRandomImages(): LiveData<List<Photo>> {
        return unsplashRepository.getRandom()
    }
}