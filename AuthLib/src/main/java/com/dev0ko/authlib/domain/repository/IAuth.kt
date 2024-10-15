package com.dev0ko.authlib.domain.repository

import com.dev0ko.authlib.domain.entity.User
import com.dev0ko.authlib.utils.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface IAuth {
    val currentUser : FirebaseUser?
    suspend fun login(user : User) : Flow<Resource<FirebaseUser?>>
    suspend fun signup(user : User) : Flow<Resource<FirebaseUser>>

    suspend fun logout(): Flow<Resource<Boolean>>
    suspend fun forgetPassword(email: String):  Flow<Resource<Boolean>>
}