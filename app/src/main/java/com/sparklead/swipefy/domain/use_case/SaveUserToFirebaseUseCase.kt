package com.sparklead.swipefy.domain.use_case

import com.google.firebase.firestore.SetOptions
import com.sparklead.core.data.model.User
import com.sparklead.swipefy.common.Resource
import com.sparklead.swipefy.domain.repository.UserFirebaseRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class SaveUserToFirebaseUseCase @Inject constructor(private val repository: UserFirebaseRepository) {

    suspend operator fun invoke(
        id: String,
        username: String,
        email: String
    ): Flow<Resource<User>> = callbackFlow {
        try {
            trySend(Resource.Loading())
            val user = User(
                id = id,
                username = username,
                email = email,
                gender = null,
                image = null
            )
            repository.saveUserToDatabase()
                .document(id)
                .set(user, SetOptions.merge())
                .addOnSuccessListener {
                    trySend(Resource.Success(user))
                }
            awaitClose { channel.close() }
        } catch (e: Exception) {
            trySend(Resource.Error(e.message.toString()))
        }
    }

}