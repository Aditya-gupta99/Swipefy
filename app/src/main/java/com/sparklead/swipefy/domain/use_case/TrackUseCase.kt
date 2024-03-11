package com.sparklead.swipefy.domain.use_case

import com.sparklead.swipefy.common.Resource
import com.sparklead.core.data.dto.track.TrackDto
import com.sparklead.swipefy.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrackUseCase @Inject constructor(private val repository: TrackRepository) {

    operator fun invoke(id: String): Flow<Resource<TrackDto>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(repository.getTrackById(id)))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

}