package com.dev0ko.authlib.utils.translate

import java.util.Locale

private enum class Language {
    en, // English
    fr // French

}

/**
 * Retrieves the localized authentication string based on the provided string identifier.
 *
 * @param idString The enum key representing the desired authentication string.
 * @return The localized string associated with the given identifier, or an empty string if not found.
 *
 * This function checks the default locale of the device and returns the corresponding
 * message from either the French or English string resources.
 */
fun getAuthString(idString: STRINGS): String {
    return when (Locale.getDefault().language) {
        Language.en.toString() -> STRINGS_FR.values().find { it.name == idString.name }?.message ?: ""
        Language.fr.toString() -> STRINGS_EN.values().find { it.name == idString.name }?.message ?: ""
        else -> ""
    }
}
