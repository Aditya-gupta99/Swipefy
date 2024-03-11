package com.sparklead.core.data.dto.randomTrack

import kotlinx.serialization.Serializable

@Serializable
data class ArtistX(

    val external_urls: ExternalUrlsXXX,

    val id: String,

    val name: String,

    val type: String,

    val uri: String
)