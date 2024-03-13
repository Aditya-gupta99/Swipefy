package com.sparklead.swipefy.domain.use_case

import com.sparklead.core.data.mapper.toUser
import com.sparklead.core.data.model.User
import com.sparklead.datastore.database.DatabaseUserQuery
import com.sparklead.swipefy.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserFromLocalDbUseCase @Inject constructor(private val databaseUserQuery: DatabaseUserQuery) {

    operator fun invoke() : Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            databaseUserQuery.getUserFromDb()?.let { Resource.Success(it.toUser()) }?.let { emit(it) }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

}