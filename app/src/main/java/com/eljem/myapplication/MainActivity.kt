package com.eljem.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.eljem.myapplication.databinding.ActivityMainBinding
import com.eljem.myapplication.model.entity.Category
import com.eljem.myapplication.model.entity.Photo

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val colors = arrayListOf<String>("#ffeb3b", "#a9e977", "#3eb7fe" , "#414697", "#fcb867", "#fe8a4d",
                                            "#5c5d5f")
        val photos = arrayListOf<Photo>(Photo("https://images.unsplash.com/photo-1493612276216-ee3925520721?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzMDM4MTJ8MHwxfHNlYXJjaHwxfHxyYW5kb218ZW58MHx8fHwxNjU4MDQ5ODgw&ixlib=rb-1.2.1&q=80&w=1080"),
                                            Photo("https://images.unsplash.com/photo-1513542789411-b6a5d4f31634?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzMDM4MTJ8MHwxfHNlYXJjaHwyfHxyYW5kb218ZW58MHx8fHwxNjU4MDQ5ODgw&ixlib=rb-1.2.1&q=80&w=1080"),
                                            Photo("https://images.unsplash.com/photo-1481349518771-20055b2a7b24?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzMDM4MTJ8MHwxfHNlYXJjaHwzfHxyYW5kb218ZW58MHx8fHwxNjU4MDQ5ODgw&ixlib=rb-1.2.1&q=80&w=1080"),
                                            Photo("https://images.unsplash.com/photo-1509281373149-e957c6296406?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzMDM4MTJ8MHwxfHNlYXJjaHw0fHxyYW5kb218ZW58MHx8fHwxNjU4MDQ5ODgw&ixlib=rb-1.2.1&q=80&w=1080"),
                                            Photo("https://images.unsplash.com/photo-1508138221679-760a23a2285b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzMDM4MTJ8MHwxfHNlYXJjaHw1fHxyYW5kb218ZW58MHx8fHwxNjU4MDQ5ODgw&ixlib=rb-1.2.1&q=80&w=1080")
            )

        val categories = arrayListOf<Category>(Category("Abstract", R.drawable.img_abstract),
                                                 Category("Nature", R.drawable.img_nature),
                                                 Category("Food", R.drawable.img_food),
                                                 Category("Travel", R.drawable.img_travel),
                                                 Category("Animals", R.drawable.img_animals),
                                                Category("Business", R.drawable.img_work),

        )


        val adapter = ColorAdapter(this, colors)

        binding.colors.layoutManager = LinearLayoutManager(this)
        binding.colors.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

            binding.colors.adapter = adapter


        val recommendadapter = RecommendAdapter(this, photos)

        binding.recommend.layoutManager = LinearLayoutManager(this)
        binding.recommend.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.recommend.adapter = recommendadapter



        val categoriesAdapter = CategoriesAdapter(this, categories)
        binding.categories.setHasFixedSize(true)
        binding.categories.itemAnimator = null
        binding.categories.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        //  binding.images.setLayoutManager(GridLayoutManager(this, 2))

        binding.categories.adapter = categoriesAdapter


    }
}