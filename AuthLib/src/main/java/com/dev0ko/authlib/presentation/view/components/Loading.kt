package com.dev0ko.authlib.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import com.dev0kch.chatbot.ui.theme.backgroundWithTransparent
import com.dev0kch.chatbot.ui.theme.primary

@Composable
fun CustomLoading(){
    Dialog(
        onDismissRequest = {}, properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier

                .fillMaxSize()
                .background(backgroundWithTransparent)
                .zIndex(1F),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(65.dp),
                color = primary,
            )
        }
    }
}
