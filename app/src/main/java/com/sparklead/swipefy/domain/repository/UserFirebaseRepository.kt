package com.sparklead.swipefy.domain.repository

import com.google.firebase.firestore.CollectionReference

interface UserFirebaseRepository {

    suspend fun saveUserToDatabase() : CollectionReference

}