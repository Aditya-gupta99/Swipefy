package com.sparklead.swipefy.data.repository

import com.sparklead.swipefy.data.dto.track.TrackDto
import com.sparklead.swipefy.data.service.SpotifyService
import com.sparklead.swipefy.domain.repository.TrackRepository
import javax.inject.Inject

class TrackRepositoryImp @Inject constructor(private val service: SpotifyService) :
    TrackRepository {

    override suspend fun getTrackById(id: String): TrackDto {
        return service.getTrack(id)
    }
}