package com.sparklead.swipefy.data.service

import com.sparklead.core.data.dto.randomTrack.RandomTrackDto
import com.sparklead.core.data.dto.track.TrackDto

interface SpotifyService {

    suspend fun getTrack(id: String): TrackDto

    suspend fun getRandomTracksList(genre: String): RandomTrackDto

    suspend fun getRecommendedSongList(
        artistList: List<String>,
        genresList: List<String>
    ): RandomTrackDto
}