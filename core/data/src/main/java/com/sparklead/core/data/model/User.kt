package com.sparklead.core.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class User(

    val id: String,

    val gender: String?,

    val username: String,

    val email: String,

    val image: String?,
) : Parcelable