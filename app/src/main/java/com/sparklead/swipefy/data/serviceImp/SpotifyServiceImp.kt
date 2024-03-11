package com.sparklead.swipefy.data.serviceImp

import android.util.Log
import com.sparklead.swipefy.BuildConfig
import com.sparklead.swipefy.common.Constants
import com.sparklead.core.data.dto.randomTrack.RandomTrackDto
import com.sparklead.core.data.dto.track.TrackDto
import com.sparklead.swipefy.data.remote.HttpRoutes
import com.sparklead.swipefy.data.service.SpotifyService
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class SpotifyServiceImp(private val client: HttpClient) : SpotifyService {

    override suspend fun getTrack(id: String): TrackDto {
        return try {
            client.get {
                url(HttpRoutes.TRACK_URL)
                header(Constants.API_KEY, BuildConfig.X_RapidAPI_Key)
                header(Constants.API_HOST, BuildConfig.X_RapidAPI_Host)
                parameter("ids", id)
                contentType(ContentType.Application.Json)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getRandomTracksList(genre: String): RandomTrackDto {
        return try {
            client.get {
                url(HttpRoutes.RANDOM_TRACKS)
                header(Constants.API_KEY, BuildConfig.X_RapidAPI_Key)
                header(Constants.API_HOST, BuildConfig.X_RapidAPI_Host)
                parameter("limit", 20)
                parameter("seed_genres", genre)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getRecommendedSongList(
        artistList: List<String>,
        genresList: List<String>
    ): RandomTrackDto {
        return try {
            client.get {
                url(HttpRoutes.RANDOM_TRACKS)
                header(Constants.API_KEY, BuildConfig.X_RapidAPI_Key)
                header(Constants.API_HOST, BuildConfig.X_RapidAPI_Host)
                parameter("limit", 10)
                parameter("seed_artists", "4YRxDV8wJFPHPTeXepOstw")
            }
        } catch (e: Exception) {
            Log.e("@@@",e.message.toString())
            throw e
        }
    }
}