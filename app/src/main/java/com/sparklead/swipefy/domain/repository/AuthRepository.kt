package com.sparklead.swipefy.domain.repository

import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun signUpUser(email: String, password: String): AuthResult

    suspend fun signInUser(email: String, password: String): AuthResult

    suspend fun signOutUser()

}