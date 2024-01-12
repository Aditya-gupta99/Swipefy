package com.sparklead.swipefy.domain.use_case

import com.sparklead.swipefy.common.Constants.randomTrackDtoToSwipeSongsWithPreview
import com.sparklead.swipefy.common.Resource
import com.sparklead.swipefy.domain.model.SwipeSong
import com.sparklead.swipefy.domain.repository.TrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RandomTracksUseCase @Inject constructor(private val repository: TrackRepository) {

    operator fun invoke(genre: String): Flow<Resource<List<SwipeSong>>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(randomTrackDtoToSwipeSongsWithPreview(repository.getRandomTracks(genre))))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}