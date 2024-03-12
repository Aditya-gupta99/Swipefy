package com.sparklead.swipefy.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.sparklead.swipefy.domain.repository.UserFirebaseRepository
import javax.inject.Inject

class UserFirebaseRepositoryImp @Inject constructor (private val firebaseFirestore: FirebaseFirestore) : UserFirebaseRepository {

    override suspend fun saveUserToDatabase(): CollectionReference {
        return firebaseFirestore.collection("user")
    }

}