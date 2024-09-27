package com.dev0ko.ewelp.presentation.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dev0ko.ewelp.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // SDK >= 33
            checkAndRequestPermission(Manifest.permission.READ_MEDIA_IMAGES)
        } else  {
            // SDK > 32 and < 33
            checkAndRequestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }


    }

    private fun checkAndRequestPermission(permission: String) {
        if (ContextCompat.checkSelfPermission(this, permission)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            requestPermission(permission)
        } else {
            Handler().postDelayed(Runnable {


                navigateToHome()


            }, 2000)
        }
    }

    private fun requestPermission(permission: String) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            // Show an explanation to the user why your app needs the permission
            AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage("This permission is needed for the app to function properly")
                .setPositiveButton("OK") { dialog, _ ->
                    ActivityCompat.requestPermissions(this,
                        arrayOf(permission), PERMISSION_REQUEST_CODE)
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
            ActivityCompat.requestPermissions(this,
                arrayOf(permission), PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission was granted, yay!
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