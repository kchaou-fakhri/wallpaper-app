package com.dev0ko.authlib

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.dev0kch.chatbot.ui.theme.ChatbotTheme
import com.dev0ko.authlib.presentation.view.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthLibraryActivity : AppCompatActivity() {
    private lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        setContent {
            ChatbotTheme {
                LoginScreen({}, null)
            }
        }
    }
}