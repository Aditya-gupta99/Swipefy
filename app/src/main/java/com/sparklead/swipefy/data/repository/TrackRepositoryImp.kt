package com.sparklead.swipefy.data.repository

import com.sparklead.core.data.dto.randomTrack.RandomTrackDto
import com.sparklead.core.data.dto.track.TrackDto
import com.sparklead.swipefy.data.service.SpotifyService
import com.sparklead.swipefy.domain.repository.TrackRepository
import javax.inject.Inject

class TrackRepositoryImp @Inject constructor(private val service: SpotifyService) :
    TrackRepository {

    override suspend fun getTrackById(id: String): TrackDto {
        return service.getTrack(id)
    }

    override suspend fun getRandomTracks(genre: String): RandomTrackDto {
        return service.getRandomTracksList(genre)
    }

}