package com.dev0ko.ewelp.presentation.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dev0ko.ewelp.data.entity.Photo
import com.dev0ko.ewelp.databinding.ActivityMainBinding
import com.dev0ko.ewelp.presentation.ui.adapter.CategoriesAdapter
import com.dev0ko.ewelp.presentation.ui.adapter.ColorAdapter
import com.dev0ko.ewelp.presentation.ui.adapter.RecommendedAdapter
import com.dev0ko.ewelp.presentation.viewmodel.PhotoViewModel
import com.dev0ko.ewelp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val photoVM: PhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)





        photoVM.getRandomImages().observe(this, Observer {
            val recommendedAdapter = RecommendedAdapter(this, it as ArrayList<Photo>)

            binding.recommend.layoutManager = LinearLayoutManager(this)
            binding.recommend.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            binding.recommend.adapter = recommendedAdapter
        })


        val adapter = ColorAdapter(this, Constants.COLORS)

        binding.colors.layoutManager = LinearLayoutManager(this)
        binding.colors.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

            binding.colors.adapter = adapter


        val categoriesAdapter = CategoriesAdapter(this, Constants.CATEGORIES)
        binding.categories.setHasFixedSize(true)
        binding.categories.itemAnimator = null
        binding.categories.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.categories.adapter = categoriesAdapter


    }
}