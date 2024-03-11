package com.sparklead.core.data.dto.randomTrack

import kotlinx.serialization.Serializable

@Serializable
data class RandomTrackDto(

    val seeds: List<Seed>,

    val tracks: List<Track>

)