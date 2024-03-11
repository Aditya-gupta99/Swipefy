package com.sparklead.swipefy.domain.use_case

import androidx.paging.PagingData
import com.sparklead.core.data.model.Song
import com.sparklead.swipefy.domain.repository.SongListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendedSongsUseCase @Inject constructor(private val repository: SongListRepository) {

    operator fun invoke(): Flow<PagingData<Song>> {
        return repository.getAllRecommendedSongs()
    }
}