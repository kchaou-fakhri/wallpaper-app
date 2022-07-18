package com.eljem.myapplication.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
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

        hideSystemUI();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setContentView(view)

        photoVM = PhotoVM()
        val category = intent.extras?.getString("category")

        photoVM.getData(category!!,30).observe(this, Observer {


            val imagesAdapter = ImagesAdapter(this,it as ArrayList<Photo> )
            binding.images.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            binding.images.setHasFixedSize(true)
            binding.images.itemAnimator = null


            //  binding.images.setLayoutManager(GridLayoutManager(this, 2))

            binding.images.adapter = imagesAdapter
        })

    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)    }
}
