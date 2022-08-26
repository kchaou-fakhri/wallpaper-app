package com.eljem.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.eljem.myapplication.R
import com.eljem.myapplication.databinding.ActivityMainBinding
import com.eljem.myapplication.model.entity.Category
import com.eljem.myapplication.model.entity.Color
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

        val colors = arrayListOf<Color>(Color("Yellow","#F9A828"),
                                         Color("Gray","#ECECEB"),
                                            Color("Navy", "#07617D"),
                                                Color("Black","#2E383F"),
                                            Color("Green", "#349E67"),
                                        Color("Blue","#3eb7fe") , Color("Purple","#7a65fe"),
                                         Color("Brown","#fcb867"),Color("Orange", "#fe8a4d"),
                                             Color("Red", "#f3212d")
                                            )


        photoVM.getData("popular",20).observe(this, Observer {
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