package com.sparklead.core.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class Artist(

    val id: String,

    val name: String,
) : Parcelable