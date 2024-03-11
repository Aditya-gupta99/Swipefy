package com.sparklead.core.data.dto.track

import kotlinx.serialization.Serializable

@Serializable
data class Album(

    val album_type: String,

    val artists: List<ArtistX>,

    val external_urls: ExternalUrlsXXX,

    val id: String,

    val images: List<Image>,

    val is_playable: Boolean,

    val name: String,

    val release_date: String,

    val release_date_precision: String,

    val total_tracks: Int,

    val type: String,

    val uri: String
)