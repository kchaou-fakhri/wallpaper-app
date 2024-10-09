package com.dev0ko.authlib.utils

fun validateEmail(email : String) : Boolean {
    return  CONSTANTS.EmailRegex.containsMatchIn(email)
}