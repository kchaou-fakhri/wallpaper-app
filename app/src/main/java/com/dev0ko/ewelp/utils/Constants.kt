package com.dev0ko.ewelp.utils

import com.dev0ko.ewelp.R
import com.dev0ko.ewelp.data.entity.Category
import com.dev0ko.ewelp.data.entity.Color

object Constants {
    val COLORS = arrayListOf<Color>(
        Color("Yellow", "#F9A828"),
        Color("Gray", "#ECECEB"),
        Color("Navy", "#07617D"),
        Color("Black", "#2E383F"),
        Color("Green", "#349E67"),
        Color("Blue", "#3eb7fe"),
        Color("Purple", "#7a65fe"),
        Color("Brown", "#fcb867"),
        Color("Orange", "#fe8a4d"),
        Color("Red", "#f3212d")
    )

    val CATEGORIES = arrayListOf<Category>(
        Category("Abstract", R.drawable.img_abstract),
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

    val AUTHORIZATION = "Authorization"

    val CLIENT_ID = "Client-ID "
}