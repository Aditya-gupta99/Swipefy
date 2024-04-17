package com.sparklead.swipefy.common

import com.sparklead.core.data.dto.randomTrack.RandomTrackDto
import com.sparklead.core.data.model.Artist
import com.sparklead.core.data.model.SwipeSong

object Constants {

    const val API_KEY = "X-RapidAPI-Key"
    const val API_HOST = "X-RapidAPI-Host"

    const val NOW_PLAYING_CHANNEL_ID = "channel 1"
    const val NOW_PLAYING_NOTIFICATION_ID = 10
    const val NOTIFICATION_CHANNEL_NAME = "Swipefy"

    private val fullScreenRoutes = listOf(
        "sign_up_screen",
        "sign_in_screen"
    )

    fun isFullScreen(route: String?): Boolean {
        return fullScreenRoutes.contains(route)
    }

    fun randomTrackDtoToSwipeSongsWithPreview(randomTrackDto: RandomTrackDto): List<SwipeSong> {
        val swipeSongList = ArrayList<SwipeSong>()
        randomTrackDto.tracks.forEach {
            if (it.preview_url != null) {
                swipeSongList.add(
                    SwipeSong(
                        id = it.id,
                        name = it.name,
                        duration = it.duration_ms,
                        previewUrl = it.preview_url!!,
                        imageUrl = it.album.images[0].url,
                        artist = it.artists.map { artistX ->
                            Artist(
                                id = artistX.id,
                                name = artistX.name
                            )
                        }
                    )
                )
            }
        }
        return swipeSongList
    }
}