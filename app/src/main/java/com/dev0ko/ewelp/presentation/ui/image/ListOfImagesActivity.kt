package com.dev0ko.ewelp.presentation.ui.image

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dev0ko.authlib.AuthLibraryActivity
import com.dev0ko.authlib.presentation.viewmodel.AuthenticationViewModel
import com.dev0ko.ewelp.data.entity.Photo
import com.dev0ko.ewelp.databinding.ActivityListOfImagesBinding
import com.dev0ko.ewelp.presentation.ui.adapter.ImagesAdapter
import com.dev0ko.ewelp.presentation.viewmodel.PhotoViewModel
import com.dev0ko.ewelp.utils.Constants
import com.dev0ko.ewelp.utils.checkOrientation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfImagesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListOfImagesBinding
    private val photoVM: PhotoViewModel by viewModels()
    private lateinit var imagesAdapter: ImagesAdapter
    private var images: ArrayList<Photo> = arrayListOf()
    var isAuthenticated = false
    private lateinit var category : String
    private val authenticationViewModel: AuthenticationViewModel by viewModels()

    // Current page for pagination
    var page = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListOfImagesBinding.inflate(layoutInflater)
        val view = binding.root

        // Hide system UI for a fullscreen experience
        // hideSystemUI()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        setContentView(view)




        binding.btnMigrate.setOnClickListener {
            this.let {
                val intent = Intent(it, AuthLibraryActivity::class.java)
                it.startActivity(intent)
            }
        }
        // Get the category from the intent extras
         category = intent.extras?.getString(Constants.CATEGORY)!!

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


        binding.parent.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val lastChild = (v as NestedScrollView).getChildAt(v.childCount - 1)
            if (lastChild != null) {
                if (scrollY >= (lastChild.measuredHeight - v.measuredHeight) && scrollY > oldScrollY && isAuthenticated) {
                    page++ // Increment page number for pagination
                    getData(category, page) // Load more data
                }
            }
        }


        authenticationViewModel.getConnectedUser().observe(this, Observer {
            if (it != null) {

                binding.migrateView.visibility = View.GONE
                isAuthenticated = true
            } else {
                isAuthenticated = false
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.migrateView.visibility = View.VISIBLE
                }, 1000)


            }
        })
    }


    private fun getData(category: String?, page: Int) {
        // Check the orientation of the device
        var orientation = checkOrientation(this)
        // Observe data from ViewModel and update the image list
        photoVM.getData(category!!, 30, page, orientation).observe(this, Observer {
            images.addAll(it) // Add new images to the list
            imagesAdapter.notifyDataSetChanged() // Notify adapter of data change

        })
    }

    override fun onResume() {
        super.onResume()

        authenticationViewModel.getConnectedUser().observe(this, Observer {
            if (it != null) {
                binding.migrateView.visibility = View.GONE
                page++ // Increment page number for pagination
                getData(category, page) // Load more data
                isAuthenticated = true
            }

        })
    }
}
