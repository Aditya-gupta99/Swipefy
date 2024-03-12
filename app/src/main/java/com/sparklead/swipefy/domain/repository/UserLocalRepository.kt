package com.sparklead.swipefy.domain.repository

import com.sparklead.core.data.model_db.UserDb

interface UserLocalRepository {

    suspend fun saveUserToLocalDb(userDb: UserDb)

}