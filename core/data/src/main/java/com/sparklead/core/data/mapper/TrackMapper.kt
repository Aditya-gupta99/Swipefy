package com.sparklead.core.data.mapper

import com.sparklead.core.data.dto.randomTrack.Track
import com.sparklead.core.data.model.Artist
import com.sparklead.core.data.model.Song

fun Track.toSong(): Song {
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