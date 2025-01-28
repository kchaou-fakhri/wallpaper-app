package com.dev0ko.authlib.utils

import com.dev0ko.authlib.presentation.signin.UserData
import com.google.firebase.auth.FirebaseUser


 fun firebaseUserToUserData(firebaseUser: FirebaseUser?): UserData? {
        return if (firebaseUser == null) null
        else  {
            UserData(
                userId = firebaseUser.uid,
                username = firebaseUser.displayName,
                profilePictureUrl = firebaseUser.photoUrl?.toString()
            )
        }
    }


