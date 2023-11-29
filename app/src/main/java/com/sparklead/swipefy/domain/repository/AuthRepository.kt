package com.sparklead.swipefy.domain.repository

import com.google.firebase.auth.AuthResult
import com.sparklead.swipefy.common.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun signUpUser(email: String, password: String): AuthResult

}