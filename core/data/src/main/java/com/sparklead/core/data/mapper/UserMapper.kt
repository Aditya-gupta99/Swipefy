package com.sparklead.core.data.mapper

import com.sparklead.core.data.model.User
import com.sparklead.core.data.model_db.UserDb

fun User.toUserDb(): UserDb = UserDb().apply {
    _id = id
    username = this@toUserDb.username
    image = this@toUserDb.image
    gender = this@toUserDb.gender
    email = this@toUserDb.email
}


fun UserDb.toUser(): User = User(
    id = _id,
    username = username,
    email = email,
    gender = gender,
    image = image
)