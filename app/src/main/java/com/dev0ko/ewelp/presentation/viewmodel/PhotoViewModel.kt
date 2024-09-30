package com.dev0ko.ewelp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dev0ko.ewelp.data.entity.Photo
import com.dev0ko.ewelp.data.repository.photos.remote.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val unsplashRepository : UnsplashRepository): ViewModel()  {

    fun getData(search: String, perPage: Int, page: Int,  orientation : String): LiveData<List<Photo>> {
        return unsplashRepository.getData(search,perPage, page, orientation )
    }

    fun getRandomImages(): LiveData<List<Photo>> {
        return unsplashRepository.getRandom()
    }
}