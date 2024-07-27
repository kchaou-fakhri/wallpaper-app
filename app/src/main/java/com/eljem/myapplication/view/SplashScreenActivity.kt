package com.eljem.myapplication.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.eljem.myapplication.R

class SplashScreenActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            requestPermission()
        } else {
            // Permission has already been granted
            // Continue with the app functionality
            Handler().postDelayed(Runnable {


                navigateToHome()


            }, 2000)
        }


    }


    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_MEDIA_IMAGES
            )
        ) {
            // Show an explanation to the user why your app needs the permission
            AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage("This permission is needed to access media files")
                .setPositiveButton("OK") { dialog, _ ->
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_MEDIA_IMAGES), PERMISSION_REQUEST_CODE
                    )
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                    closeApp()
                }
                .create()
                .show()
        } else {
            // No explanation needed, we can request the permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES), PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission was granted, yay! Do the task you need to do.
                    navigateToHome()
                } else {
                    // Permission denied, boo! Close the app.
                    closeApp()
                }
                return
            }
            // Add other 'when' lines to check for other permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun closeApp() {
        finishAffinity() // Close the app
    }

    private fun navigateToHome() {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}