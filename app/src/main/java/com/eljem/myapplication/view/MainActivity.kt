package com.eljem.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.eljem.myapplication.R
import com.eljem.myapplication.databinding.ActivityMainBinding
import com.eljem.myapplication.model.entity.Category
import com.eljem.myapplication.model.entity.Photo
import com.eljem.myapplication.vm.PhotoVM

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var photoVM : PhotoVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        photoVM = PhotoVM()

        val colors = arrayListOf<String>("#ffeb3b", "#a9e977", "#3eb7fe" , "#414697", "#fcb867", "#fe8a4d",
                                            "#5c5d5f")


        photoVM.getData("wallpapers",8).observe(this, Observer {
            val recommendadapter = RecommendAdapter(this, it as ArrayList<Photo>)

            binding.recommend.layoutManager = LinearLayoutManager(this)
            binding.recommend.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            binding.recommend.adapter = recommendadapter
        })


        val categories = arrayListOf<Category>(Category("Abstract", R.drawable.img_abstract),
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
        binding.categories.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        //  binding.images.setLayoutManager(GridLayoutManager(this, 2))

        binding.categories.adapter = categoriesAdapter


    }
}