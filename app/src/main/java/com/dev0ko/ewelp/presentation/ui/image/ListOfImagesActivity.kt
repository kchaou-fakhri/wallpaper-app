package com.dev0ko.ewelp.presentation.ui.image

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dev0ko.ewelp.data.entity.Photo
import com.dev0ko.ewelp.databinding.ActivityListOfImagesBinding
import com.dev0ko.ewelp.presentation.ui.adapter.ImagesAdapter
import com.dev0ko.ewelp.presentation.vm.PhotoVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ListOfImagesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListOfImagesBinding
    private val photoVM: PhotoVM by viewModels()
    private lateinit var imagesAdapter: ImagesAdapter
    private var images: ArrayList<Photo> = arrayListOf()
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListOfImagesBinding.inflate(layoutInflater)
        val view = binding.root

        hideSystemUI();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setContentView(view)


        val category = intent.extras?.getString("category")


        imagesAdapter = ImagesAdapter(this, images)
        binding.images.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.images.setHasFixedSize(true)
        binding.images.itemAnimator = null
        binding.images.adapter = imagesAdapter

        getData(category, page)





        binding.images.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    page++
                    getData(category, page)
                }
            }
        })

    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
    private fun getData(category: String?, page: Int) {
        photoVM.getData(category!!, 30, page).observe(this, Observer {
            images.addAll(it)
            imagesAdapter.notifyDataSetChanged()
        })
    }
}
