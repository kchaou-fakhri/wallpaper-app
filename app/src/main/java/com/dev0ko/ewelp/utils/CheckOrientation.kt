package com.dev0ko.ewelp.utils

import android.content.Context
import android.content.res.Configuration


/**
 * Checks the orientation based on the device's screen layout size.
 *
 * @param context The context from which to access the device's resources and configuration.
 * @return A string representing the orientation: either Constants.LANDSCAPE or Constants.PORTRAIT,
 * depending on whether the screen layout is large or not.
 */
fun checkOrientation(context: Context): String =
     if ((context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE) Constants.LANDSCAPE else Constants.PORTRAIT
