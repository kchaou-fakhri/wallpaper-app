package com.dev0ko.authlib.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dev0ko.authlib.R
import com.dev0ko.authlib.presentation.viewmodel.AuthenticationViewModel
import com.dev0ko.authlib.utils.GlobalStyles
import com.dev0ko.authlib.utils.findActivity

@Composable
fun HomeScreen(
    navController: NavHostController,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.background))
            .padding(
                top = 25.dp,
                start = GlobalStyles.Padding.ScreenPadding,
                end = GlobalStyles.Padding.ScreenPadding
            )
            .fillMaxSize()

    ) {
        Button(onClick = {
            authenticationViewModel.logout().also {
                context.findActivity()?.finish()
            }
        }) {
            Text(text = "Close")
        }
    }
}
