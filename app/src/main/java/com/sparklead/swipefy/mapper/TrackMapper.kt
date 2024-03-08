package com.sparklead.swipefy.mapper

import com.sparklead.swipefy.data.dto.randomTrack.Track
import com.sparklead.swipefy.domain.model.Artist
import com.sparklead.swipefy.domain.model.Song

fun Track.toSong() : Song {
    return Song(
        id = id,
        name = name,
        duration = duration_ms,
        previewUrl = preview_url,
        imageUrl = album.images[0].url,
        artist = artists.map { artistX ->
            Artist(
                id = artistX.id,
                name = artistX.name
            )
        }
    )
}