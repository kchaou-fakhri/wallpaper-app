package com.eljem.myapplication.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.eljem.myapplication.R

class LoadingAlert internal constructor(private val activity: Activity) {
    private lateinit var alertDialog : AlertDialog
    fun startLoadingAlert() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading_alert, null))
        builder.setCancelable(false)
        alertDialog = builder.create()
        alertDialog.show()
    }

    fun dismissDialog() {
        alertDialog.cancel()

    }

    fun showDialog() {
        alertDialog.show()

    }
}