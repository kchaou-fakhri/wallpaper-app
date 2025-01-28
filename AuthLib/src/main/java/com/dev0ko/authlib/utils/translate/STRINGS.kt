package com.dev0ko.authlib.utils.translate

enum class STRINGS {
    ENTER_VALID_EMAIL,
    EMAIL_IS_REQUIRED,
    USERNAME_IS_REQUIRED,
    PASSWORD_IS_REQUIRED,
    PASSWORD_MUST_BE_LEAST_6CH
}

enum class STRINGS_FR(val message: String) {
    ENTER_VALID_EMAIL("Veuillez saisir une adresse email"),
    EMAIL_IS_REQUIRED("L'email électronique est requise"),
    USERNAME_IS_REQUIRED("Le nom d'utilisateur est requis"),
    PASSWORD_IS_REQUIRED("Le mot de passe est requis"),
    PASSWORD_MUST_BE_LEAST_6CH("Le mot de passe doit comporter au moins 6 caractères");
}

enum class STRINGS_EN(val message: String) {
    ENTER_VALID_EMAIL("Please enter a valid email address"),
    EMAIL_IS_REQUIRED("Email is required"),
    USERNAME_IS_REQUIRED("Username is required"),
    PASSWORD_IS_REQUIRED("Password is required"),
    PASSWORD_MUST_BE_LEAST_6CH("Password must be at least 6 characters")
}