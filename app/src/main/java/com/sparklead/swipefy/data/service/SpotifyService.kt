package com.sparklead.swipefy.data.service

import com.sparklead.swipefy.data.dto.randomTrack.RandomTrackDto
import com.sparklead.swipefy.data.dto.track.TrackDto

interface SpotifyService {

    suspend fun getTrack(id: String): TrackDto

    suspend fun getRandomTracksList(genre: String): RandomTrackDto
}