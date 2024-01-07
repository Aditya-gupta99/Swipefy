package com.sparklead.swipefy.domain.repository

import com.sparklead.swipefy.data.dto.track.TrackDto

interface TrackRepository {

    suspend fun getTrackById(id: String): TrackDto

}