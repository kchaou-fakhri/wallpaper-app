package com.dev0ko.authlib.presentation.signin

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.dev0kch.chatbot.data.utils.await
import com.dev0ko.authlib.R
import com.dev0ko.authlib.utils.Resource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.flow
import java.util.concurrent.CancellationException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GoogleAuthUiClient @Inject constructor(
    private val context: Context,
    private val oneTapClient: SignInClient,
    private val auth: FirebaseAuth?
) {

    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(buildSignInRequest()).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }


    suspend fun signInWithIntent(intent: Intent): Flow<Resource<FirebaseUser>> {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

        return flow {
            try {
                emit(Resource.Loading)
                val user = auth
                    ?.signInWithCredential(googleCredentials)?.await()?.user
                Resource.Success(
                    user?.run {
                        UserData(
                            userId = uid,
                            username = displayName,
                            profilePictureUrl = photoUrl?.toString()
                        )
                    }

                )
                emit(Resource.Success(user!!))

            } catch (e: Exception) {
                e.printStackTrace()
                if (e is CancellationException) throw e
                emit(Resource.Failure(e))
            }
        }

    }


        private fun buildSignInRequest(): BeginSignInRequest {
            return BeginSignInRequest.Builder()
                .setGoogleIdTokenRequestOptions(
                    GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setFilterByAuthorizedAccounts(false)
                        .setServerClientId(context.getString(R.string.web_client_id))
                        .build()
                )
                .setAutoSelectEnabled(true)
                .build()
        }
    }