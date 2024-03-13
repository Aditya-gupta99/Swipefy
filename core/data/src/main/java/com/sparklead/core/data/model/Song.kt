package com.sparklead.core.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(

    val id: String,

    val name: String,

    val duration: Int,

    val previewUrl: String?,

    val imageUrl: String,

    val artist: List<Artist>
) : Parcelable