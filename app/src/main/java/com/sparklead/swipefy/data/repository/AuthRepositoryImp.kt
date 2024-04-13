package com.sparklead.swipefy.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.sparklead.swipefy.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthRepository {

    override suspend fun signUpUser(email: String, password: String): AuthResult {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun signInUser(email: String, password: String): AuthResult {
        return firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun signOutUser() {
        firebaseAuth.signOut()
    }
}