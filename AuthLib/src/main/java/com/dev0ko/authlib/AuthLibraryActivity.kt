package com.dev0ko.authlib

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.dev0kch.chatbot.ui.theme.ChatbotTheme
import com.dev0ko.authlib.presentation.navigation.HomeNavigation
import com.dev0ko.authlib.presentation.navigation.Route
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthLibraryActivity :
    AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setContent {
            ChatbotTheme {
                val navController = rememberNavController()
                HomeNavigation(
                    navController = navController,
                    startDestination = Route.LoginScreen.route
                )

            }
        }
    }

}