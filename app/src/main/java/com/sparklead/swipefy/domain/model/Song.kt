package com.sparklead.swipefy.domain.model

data class Song(

    val id: String,

    val name: String,

    val duration: Int,

    val previewUrl: String?,

    val imageUrl: String,

    val artist: List<Artist>
)