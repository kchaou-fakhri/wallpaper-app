package com.eljem.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.eljem.myapplication.databinding.ActivityListOfImagesBinding
import com.eljem.myapplication.model.entity.Photo
import com.eljem.myapplication.vm.PhotoVM

class ListOfImagesActivity : AppCompatActivity() {
    lateinit var binding: ActivityListOfImagesBinding
    lateinit var photoVM : PhotoVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListOfImagesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        photoVM = PhotoVM()
        val category = intent.extras?.getString("category")

        photoVM.getData(category!!,30).observe(this, Observer {


            val imagesAdapter = ImagesAdapter(this, it as ArrayList<Photo>)
            binding.images.setHasFixedSize(true)
            binding.images.itemAnimator = null
            binding.images.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            //  binding.images.setLayoutManager(GridLayoutManager(this, 2))

            binding.images.adapter = imagesAdapter
        })

    }
}