package com.dev0ko.authlib.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev0ko.authlib.data.repository.remote.auth.AuthRepositoryImpl
import com.dev0ko.authlib.domain.entity.User
import com.dev0ko.authlib.presentation.signin.UserData
import com.dev0ko.authlib.utils.Resource
import com.dev0ko.authlib.utils.firebaseUserToUserData
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl,
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser?>?>(null)
    val loginFLow: StateFlow<Resource<FirebaseUser?>?> = _loginFlow

    private val _forgetPassword = MutableStateFlow<Resource<Boolean?>?>(null)
    val forgetPassword : StateFlow<Resource<Boolean?>?> = _forgetPassword

    private val _isAuthenticated = MutableStateFlow<Boolean>(false)
    val isAuthenticated : StateFlow<Boolean> = _isAuthenticated


    val currentUser: FirebaseUser?
        get() = authRepositoryImpl.currentUser

    init {
        if (authRepositoryImpl.currentUser != null) {
            _loginFlow.value = Resource.Success(authRepositoryImpl.currentUser!!)
            _isAuthenticated.value = true // Set authenticated status correctly if user is found
        } else {
            _isAuthenticated.value = false
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginFlow.value = Resource.Loading
            authRepositoryImpl.login(User(email, password, null)).collect { resource ->
                _loginFlow.value = resource

                if (resource is Resource.Success && resource.result != null) {
                    _isAuthenticated.value = true
                    Log.d("AuthenticationViewModel", "User logged in successfully.")
                }
            }
        }
    }

    fun forgetPassword(email: String) {
        viewModelScope.launch {
            _forgetPassword.value = Resource.Loading
            authRepositoryImpl.forgetPassword(email).collect { resource ->
                _forgetPassword.value = resource
            }
        }
    }


    fun signUp(email: String, password: String, name: String) {
        _loginFlow.value = Resource.Loading

        viewModelScope.launch {
            authRepositoryImpl.signup(User(email, password, name)).collect { resource ->
                _loginFlow.value = resource

                if (resource is Resource.Success && resource.result != null) {
                    _isAuthenticated.value = true
                }
            }

        }
    }


    fun logout() {
        viewModelScope.launch {
            authRepositoryImpl.logout().collect { resource ->
                if (resource is Resource.Success && resource.result != null) {
                    _isAuthenticated.value = false
                    Log.d("AuthenticationViewModel", "User logged in successfully.")
                }
            }
        }
     }



    suspend fun onSignInResult(result: Flow<Resource<FirebaseUser>>) {
         result.collect { resource ->
             _loginFlow.value = resource

             if (resource is Resource.Success && resource.result != null) {
                 _isAuthenticated.value = true
                 Log.d("AuthenticationViewModel", "User logged in successfully.")
             }
        }
    }


    fun getConnectedUser(): LiveData<UserData?> {
        val mutableLiveData = MutableLiveData<UserData?>()
        mutableLiveData.value = firebaseUserToUserData(currentUser)
        return mutableLiveData
    }


}