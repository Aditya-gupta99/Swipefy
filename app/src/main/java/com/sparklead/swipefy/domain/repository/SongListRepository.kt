package com.sparklead.swipefy.domain.repository

import androidx.paging.PagingData
import com.sparklead.swipefy.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface SongListRepository {

    fun getAllRecommendedSongs(): Flow<PagingData<Song>>

}