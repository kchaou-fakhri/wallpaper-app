package com.dev0ko.authlib.utils

import com.dev0ko.authlib.utils.translate.STRINGS
import com.dev0ko.authlib.utils.translate.getAuthString

fun validateEmail(email : String) : Boolean {
    return  CONSTANTS.EmailRegex.containsMatchIn(email)
}

fun validateCredentials(email: String, password: String): Pair<String, String> {
    var emailError = ""
    var passwordError = ""


    if (email.isEmpty()) {
        emailError = getAuthString(STRINGS.EMAIL_IS_REQUIRED)
    }



    if (password.isEmpty()) {
        passwordError = getAuthString(STRINGS.PASSWORD_IS_REQUIRED)

    }
    if (password.length <= 6 && !password.isEmpty()) {
        passwordError = getAuthString(STRINGS.PASSWORD_MUST_BE_LEAST_6CH)

    }
    if (!validateEmail(email) && !email.isEmpty()) {
        emailError = getAuthString(STRINGS.ENTER_VALID_EMAIL)

    }


    return Pair(emailError, passwordError)
}

