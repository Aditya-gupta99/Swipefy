package com.sparklead.swipefy.domain.use_case

import com.google.firebase.auth.AuthResult
import com.sparklead.swipefy.common.Resource
import com.sparklead.swipefy.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.signInUser(email, password)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}