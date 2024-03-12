package com.sparklead.core.data.model_db

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class UserDb : RealmObject {

    @PrimaryKey
    var _id: String = ""

    var gender: String? = null

    var username: String = ""

    var email: String = ""

    var image: String? = null
}