package com.dev0ko.ewelp.presentation.ui.image


import android.Manifest
import android.app.WallpaperManager
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.dev0ko.ewelp.R
import com.dev0ko.ewelp.databinding.ActivityDisplayFullImageBinding
import com.dev0ko.ewelp.utils.Constants
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

@AndroidEntryPoint
class DisplayFullImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayFullImageBinding
    private lateinit var wallpaperManager: WallpaperManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayFullImageBinding.inflate(layoutInflater)
        val view = binding.root

        hideSystemUI();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setContentView(view)


        val photoUrl = intent.extras!!.getString(Constants.PHOTO)
        wallpaperManager = WallpaperManager.getInstance(this);


      Picasso.get().load(photoUrl).into(binding.image)




        binding.back.setOnClickListener {
            finish()
        }



        binding.btnApply.setOnClickListener {
            setWallpaper(photoUrl!!)
            it.setBackgroundResource(R.drawable.bkg_cercil_blue)

        }



        binding.btnSave.setOnClickListener {
            isStoragePermissionGranted()
            val image = doInBackground(photoUrl!!)
            saveImageToDownloadFolder(getRandomString(20) + Constants.JPEG, image!!)
            it.setBackgroundResource(R.drawable.bkg_cercil_blue)

        }
    }








    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private fun setWallpaper(photoUrl : String){

        try {

            binding.image.buildDrawingCache()
            val bmap: Bitmap =  binding.image.getDrawingCache()

                   wallpaperManager.setBitmap(bmap);
            val mySnackbar = Snackbar.make(binding.main, "Successful Operation", Snackbar.LENGTH_LONG)
            mySnackbar.setDuration(2000)



        } catch (e: IOException) {
            // here the errors can be logged instead of printStackTrace
            e.printStackTrace()
        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)    }


    fun isStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("LOG", "Permission granted")
                true
            } else {
                Log.v("LOG", "Permission revoked")
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
                false
            }
        } else {
            Log.v("LOG", "Permission is granted")
            true
        }
    }


     fun doInBackground(urlAddress: String): Bitmap? {

        var bitmap: Bitmap? = null
        try {
            binding.image.buildDrawingCache()
           bitmap =   binding.image.getDrawingCache()


        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }


    fun saveImageToDownloadFolder(imageFile: String, ibitmap: Bitmap) {
        try {
            val filePath = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                imageFile
            )
            val outputStream: OutputStream = FileOutputStream(filePath)
            ibitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            Toast.makeText(
                this,
                imageFile + "Sucessfully saved in Download Folder",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

}