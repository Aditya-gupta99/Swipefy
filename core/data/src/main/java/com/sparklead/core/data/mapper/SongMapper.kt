package com.sparklead.core.data.mapper

import com.sparklead.core.data.model.Artist
import com.sparklead.core.data.model.Song
import com.sparklead.core.data.model_db.ArtistDb
import com.sparklead.core.data.model_db.SongDb
import io.realm.kotlin.ext.toRealmList

fun Song.toSongDb(): SongDb {

    return SongDb().apply {
        _id = id
        name = this@toSongDb.name
        duration = this@toSongDb.duration
        previewUrl = this@toSongDb.previewUrl
        imageUrl = this@toSongDb.imageUrl
        artist = this@toSongDb.artist.map {
            ArtistDb().apply {
                id = it.id
                name = it.name
            }
        }.toRealmList()
    }
}

fun SongDb.toSong(): Song {

    return Song(
        id = _id,
        name = name,
        duration = duration,
        previewUrl = previewUrl,
        imageUrl = imageUrl,
        artist = artist.map {
            Artist(
                it.id,
                it.name
            )
        }
    )
}