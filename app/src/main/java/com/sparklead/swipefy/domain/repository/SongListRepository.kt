package com.sparklead.swipefy.domain.repository

import androidx.paging.PagingData
import com.sparklead.core.data.model.Song
import com.sparklead.core.data.model_db.SongDb
import kotlinx.coroutines.flow.Flow

interface SongListRepository {

    fun getAllRecommendedSongs(): Flow<PagingData<Song>>

    suspend fun downloadSong(songDb: SongDb)
}