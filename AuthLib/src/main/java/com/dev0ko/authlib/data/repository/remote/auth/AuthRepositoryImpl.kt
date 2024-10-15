package com.dev0ko.authlib.data.repository.remote.auth

import com.dev0kch.chatbot.data.utils.await
import com.dev0ko.authlib.domain.entity.User
import com.dev0ko.authlib.domain.repository.IAuth
import com.dev0ko.authlib.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : IAuth {

    /**
     * @return The currently authenticated FirebaseUser, or null if no user is logged in.
     */
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser


    /**
     * Logs in a user using email and password.
     * @param user The User entity containing email and password.
     * @return A Flow emitting a Resource object indicating success or failure,
     *         with the logged-in FirebaseUser or an exception.
     */
    override suspend fun login(user: User): Flow<Resource<FirebaseUser?>> {

        return flow {

            try {
                emit(Resource.Loading)
                val result =
                    firebaseAuth.signInWithEmailAndPassword(user.email, user.password!!).await()

                emit(Resource.Success(result.user!!))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Failure(e))
            }
        }
    }

    /**
     * Signs up a new user using email and password, and updates the profile with the user's name.
     * @param user The User entity containing email, password, and display name.
     * @return A Flow emitting a Resource object with the newly created FirebaseUser or an exception.
     */
    override suspend fun signup(user: User): Flow<Resource<FirebaseUser>> {

        return flow {
            try {
                val result =
                    firebaseAuth.createUserWithEmailAndPassword(user.email, user.password!!).await()
                result.user?.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(user.name).build()
                )?.await()
                emit(Resource.Success(result.user!!))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Failure(e))
            }

        }
    }

    /**
     * Logs out the current user.
     * @return A Flow emitting a Resource object indicating success or failure of the logout operation.
     */
    override suspend fun logout(): Flow<Resource<Boolean>> {

        return flow {
            try {

                firebaseAuth.signOut()
                Resource.Success(true)
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Failure(e)
            }

        }
    }

    /**
     * Sends a password reset email to the specified email address.
     * @param email The email address where the password reset email should be sent.
     * @return A Flow emitting a Resource object indicating success or failure of the operation.
     */
    override suspend fun forgetPassword(email: String): Flow<Resource<Boolean>> {
        return flow {
            try {
                firebaseAuth.sendPasswordResetEmail(email).await()
                emit(Resource.Success(true))
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Failure(e)
            }
        }


    }

}