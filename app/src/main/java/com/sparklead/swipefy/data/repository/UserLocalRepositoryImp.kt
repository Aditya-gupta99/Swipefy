package com.sparklead.swipefy.data.repository

import com.sparklead.core.data.model_db.UserDb
import com.sparklead.datastore.database.DatabaseUserQuery
import com.sparklead.swipefy.domain.repository.UserLocalRepository
import javax.inject.Inject

class UserLocalRepositoryImp @Inject constructor(private val databaseUserQuery: DatabaseUserQuery) :
    UserLocalRepository {

    override suspend fun saveUserToLocalDb(userDb: UserDb) {
        databaseUserQuery.updateUserToDb(userDb)
    }
}