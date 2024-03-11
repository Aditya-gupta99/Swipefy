package com.sparklead.core.data.dto.randomTrack

import kotlinx.serialization.Serializable

@Serializable
data class Seed(

    val afterFilteringSize: Int,

    val afterRelinkingSize: Int,

    val id: String,

    val initialPoolSize: Int,

    val type: String

)