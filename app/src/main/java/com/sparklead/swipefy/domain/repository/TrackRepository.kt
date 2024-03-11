package com.sparklead.swipefy.domain.repository

import com.sparklead.core.data.dto.randomTrack.RandomTrackDto
import com.sparklead.core.data.dto.track.TrackDto

interface TrackRepository {

    suspend fun getTrackById(id: String): TrackDto

    suspend fun getRandomTracks(genre: String): RandomTrackDto

}