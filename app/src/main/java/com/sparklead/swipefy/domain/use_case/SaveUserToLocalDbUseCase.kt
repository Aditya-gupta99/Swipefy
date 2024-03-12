package com.sparklead.swipefy.domain.use_case

import com.sparklead.core.data.mapper.toUserDb
import com.sparklead.core.data.model.User
import com.sparklead.swipefy.domain.repository.UserLocalRepository
import javax.inject.Inject

class SaveUserToLocalDbUseCase @Inject constructor(private val userLocalRepository: UserLocalRepository) {

    suspend operator fun invoke(user: User) = userLocalRepository.saveUserToLocalDb(user.toUserDb())

}