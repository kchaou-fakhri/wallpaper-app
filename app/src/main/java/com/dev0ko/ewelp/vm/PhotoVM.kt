package com.dev0ko.ewelp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dev0ko.ewelp.model.entity.Photo
import com.dev0ko.ewelp.model.repository.UnsplashRepository

class PhotoVM : ViewModel() {

    var unsplashRepository : UnsplashRepository

    init {
        unsplashRepository = UnsplashRepository()
    }

    fun getData(search: String, per_page: Int, page: Int): LiveData<List<Photo>> {
        return unsplashRepository.getData(search,per_page, page)
    }

    fun getRandomImages(): LiveData<List<Photo>> {
        return unsplashRepository.getRandom()
    }
}