package com.sparklead.swipefy.data.dto.track

import kotlinx.serialization.Serializable

@Serializable
data class Image(

    val height: Int,

    val url: String,

    val width: Int

)