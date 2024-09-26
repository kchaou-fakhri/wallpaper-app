package com.dev0ko.ewelp.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dev0ko.ewelp.R
import com.dev0ko.ewelp.databinding.ActivityMainBinding
import com.dev0ko.ewelp.model.entity.Category
import com.dev0ko.ewelp.model.entity.Color
import com.dev0ko.ewelp.model.entity.Photo
import com.dev0ko.ewelp.view.adapter.CategoriesAdapter
import com.dev0ko.ewelp.view.adapter.ColorAdapter
import com.dev0ko.ewelp.view.adapter.RecommendAdapter
import com.dev0ko.ewelp.view.image.ListOfImagesActivity
import com.dev0ko.ewelp.vm.PhotoVM

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var photoVM: PhotoVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        photoVM = PhotoVM()

        val colors = arrayListOf<Color>(
            Color("Yellow", "#F9A828"),
            Color("Gray", "#ECECEB"),
            Color("Navy", "#07617D"),
            Color("Black", "#2E383F"),
            Color("Green", "#349E67"),
            Color("Blue", "#3eb7fe"), Color("Purple", "#7a65fe"),
            Color("Brown", "#fcb867"), Color("Orange", "#fe8a4d"),
            Color("Red", "#f3212d")
        )


        photoVM.getRandomImages().observe(this, Observer {
            val recommendadapter = RecommendAdapter(this, it as ArrayList<Photo>)

            binding.recommend.layoutManager = LinearLayoutManager(this)
            binding.recommend.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            binding.recommend.adapter = recommendadapter
        })
        binding.searchField.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                val searchText = binding.searchField.text.toString()
                Log.println(Log.ASSERT, "searched value", searchText)
                this.let {
                    val intent = Intent(it, ListOfImagesActivity::class.java)
                    intent.putExtra("category", searchText.lowercase())
                    it.startActivity(intent)
                }
                true
            } else {
                false
            }
        }

        val categories = arrayListOf<Category>(
            Category("Abstract", R.drawable.img_abstract),
            Category("Nature", R.drawable.img_nature),
            Category("Food", R.drawable.img_food),
            Category("Travel", R.drawable.img_travel),
            Category("Animals", R.drawable.img_animals),
            Category("Business", R.drawable.img_work),
            Category("Space", R.drawable.img_space),
            Category("Car", R.drawable.img_car),
            Category("Baby", R.drawable.img_baby),
            Category("Technology", R.drawable.img_tech),


            )


        val adapter = ColorAdapter(this, colors)

        binding.colors.layoutManager = LinearLayoutManager(this)
        binding.colors.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.colors.adapter = adapter


        val categoriesAdapter = CategoriesAdapter(this, categories)
        binding.categories.setHasFixedSize(true)
        binding.categories.itemAnimator = null
        binding.categories.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        //  binding.images.setLayoutManager(GridLayoutManager(this, 2))

        binding.categories.adapter = categoriesAdapter


    }
}