package com.dev0ko.authlib.presentation.navigation

sealed class Route(val route: String) {
    object HomeScreen : Route(route = "HomeScreen")
    object LoginScreen : Route(route = "LoginScreen")
}
