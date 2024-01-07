package com.sparklead.swipefy.data.service

import com.sparklead.swipefy.data.dto.track.TrackDto

interface SpotifyService {

    suspend fun getTrack(id: String): TrackDto

}