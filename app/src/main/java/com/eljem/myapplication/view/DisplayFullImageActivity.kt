package com.eljem.myapplication.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.eljem.myapplication.R
import com.eljem.myapplication.databinding.ActivityDisplayFullImageBinding
import com.eljem.myapplication.utils.ScaleImageView


class DisplayFullImageActivity : AppCompatActivity() {
    lateinit var binding : ActivityDisplayFullImageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayFullImageBinding.inflate(layoutInflater)
        val view = binding.root

        hideSystemUI();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        setContentView(view)

        val photoUrl = intent.extras!!.getString("photo")

        Glide.with(this /* context */)
            .load(photoUrl)
            .into(binding.image)

//        binding.back.setOnClickListener {
//            finish()
//        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)    }
}