package com.sparklead.swipefy.data.serviceImp

import com.sparklead.swipefy.data.dto.track.TrackDto
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
                header("X-RapidAPI-Key", "c6a3281953msh3dcc2de0c17501cp133d9djsncc5157913c65")
                header("X-RapidAPI-Host", "spotify23.p.rapidapi.com")
                parameter("ids", id)
                contentType(ContentType.Application.Json)
            }
        } catch (e: Exception) {
            throw e
        }
    }

}