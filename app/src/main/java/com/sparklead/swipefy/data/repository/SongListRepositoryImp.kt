package com.sparklead.swipefy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sparklead.swipefy.data.paging.SongPagingSource
import com.sparklead.swipefy.data.service.SpotifyService
import com.sparklead.swipefy.domain.model.Song
import com.sparklead.swipefy.domain.repository.SongListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SongListRepositoryImp @Inject constructor(private val service: SpotifyService) :
    SongListRepository {
    override fun getAllRecommendedSongs(): Flow<PagingData<Song>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SongPagingSource(service)
            }
        ).flow
    }
}