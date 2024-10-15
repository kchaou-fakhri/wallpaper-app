package com.dev0ko.authlib.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dev0ko.authlib.presentation.view.ForgetPasswordScreen
import com.dev0ko.authlib.presentation.view.HomeScreen
import com.dev0ko.authlib.presentation.view.LoginScreen
import com.dev0ko.authlib.presentation.view.RegisterScreen


@Composable
fun HomeNavigation(navController: NavHostController, startDestination: String) {

    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Route.HomeScreen.route) {
            HomeScreen(navController)
        }

        composable(route = Route.LoginScreen.route) {
            LoginScreen(navController)
        }

        composable(route = Route.RegisterScreen.route) {
            RegisterScreen(navController)
        }

        composable(route = Route.ForgetPasswordScreen.route) {
            ForgetPasswordScreen(navController)
        }
    }
}



