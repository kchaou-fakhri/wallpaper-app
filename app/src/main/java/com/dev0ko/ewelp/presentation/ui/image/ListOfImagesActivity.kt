package com.dev0ko.ewelp.presentation.ui.image

import android.os.Build
import android.os.Bundle
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
import com.dev0ko.ewelp.presentation.viewmodel.PhotoViewModel
import com.dev0ko.ewelp.utils.Constants
import com.dev0ko.ewelp.utils.checkOrientation
import dagger.hilt.android.AndroidEntryPoint

// Entry point for dependency injection using Hilt
@AndroidEntryPoint
class ListOfImagesActivity : AppCompatActivity() {
    // View binding for the activity
    private lateinit var binding: ActivityListOfImagesBinding
    // ViewModel to manage photo data
    private val photoVM: PhotoViewModel by viewModels()
    // Adapter for displaying images in a RecyclerView
    private lateinit var imagesAdapter: ImagesAdapter
    // List to hold images
    private var images: ArrayList<Photo> = arrayListOf()
    // Current page for pagination
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityListOfImagesBinding.inflate(layoutInflater)
        val view = binding.root

        // Hide system UI for a fullscreen experience
        hideSystemUI()
        // Set layout in display cutout mode for devices with a notch
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        // Show the action bar if it's not hidden
        supportActionBar?.show()
        // Set the content view to the inflated layout
        setContentView(view)

        // Get the category from the intent extras
        val category = intent.extras?.getString(Constants.CATEGORY)

        // Initialize the adapter with the current context and image list
        imagesAdapter = ImagesAdapter(this, images)
        // Set a StaggeredGridLayoutManager with 3 columns
        binding.images.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        // Optimize RecyclerView performance
        binding.images.setHasFixedSize(true)
        binding.images.itemAnimator = null
        // Set the adapter to the RecyclerView
        binding.images.adapter = imagesAdapter

        // Load initial data for the given category and page
        getData(category, page)

        // Set up a scroll listener to load more data when scrolled to the bottom
        binding.images.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // Check if RecyclerView cannot scroll further down
                if (!recyclerView.canScrollVertically(1)) {
                    page++ // Increment page number for pagination
                    getData(category, page) // Load more data
                }
            }
        })
    }

    // Function to hide the system UI for a fullscreen experience
    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    // Function to fetch data based on category and page
    private fun getData(category: String?, page: Int) {
        // Check the orientation of the device
        var orientation = checkOrientation(this)
        // Observe data from ViewModel and update the image list
        photoVM.getData(category!!, 30, page, orientation).observe(this, Observer {
            images.addAll(it) // Add new images to the list
            imagesAdapter.notifyDataSetChanged() // Notify adapter of data change
        })
    }
}
