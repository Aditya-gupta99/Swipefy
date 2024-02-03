package com.sparklead.swipefy.data.dto.randomTrack

import kotlinx.serialization.Serializable

@Serializable
data class Image(

    val height: Int,

    val url: String,

    val width: Int

)