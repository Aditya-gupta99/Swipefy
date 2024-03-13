package com.sparklead.datastore.database

import com.sparklead.core.data.model_db.UserDb
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import javax.inject.Inject

class DatabaseUserQuery @Inject constructor(private val realm: Realm) {

    suspend fun updateUserToDb(userDb: UserDb) {
        realm.write {
            val user = query<UserDb>(query = "_id == $0", userDb._id).first().find()
            user?.apply {
                username = userDb.username
                email = userDb.email
                image = userDb.username
                gender = userDb.gender
            } ?: copyToRealm(userDb)
        }
    }

    fun getUserFromDb(): UserDb? {
        return realm.query<UserDb>().first().find()
    }

}