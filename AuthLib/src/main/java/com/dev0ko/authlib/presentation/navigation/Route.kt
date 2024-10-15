package com.dev0ko.authlib.presentation.navigation

sealed class Route(val route: String) {
    data object HomeScreen : Route(route = "HomeScreen")
    data object LoginScreen : Route(route = "LoginScreen")
    data object RegisterScreen : Route(route = "RegisterScreen")
    data object ForgetPasswordScreen : Route(route = "ForgetPasswordScreen")


}
