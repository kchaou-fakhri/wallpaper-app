package com.dev0ko.ewelp.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dev0ko.ewelp.data.entity.Photo
import com.dev0ko.ewelp.data.repository.photos.remote.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoVM @Inject constructor(private val unsplashRepository : UnsplashRepository): ViewModel()  {

    fun getData(search: String, per_page: Int, page: Int): LiveData<List<Photo>> {
        return unsplashRepository.getData(search,per_page, page)
    }

    fun getRandomImages(): LiveData<List<Photo>> {
        return unsplashRepository.getRandom()
    }
}