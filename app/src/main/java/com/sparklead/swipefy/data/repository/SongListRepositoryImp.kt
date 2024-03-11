package com.sparklead.swipefy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sparklead.core.data.model.Song
import com.sparklead.core.data.model_db.SongDb
import com.sparklead.datastore.database.DatabaseSongQuery
import com.sparklead.swipefy.data.paging.SongPagingSource
import com.sparklead.swipefy.data.service.SpotifyService
import com.sparklead.swipefy.domain.repository.SongListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SongListRepositoryImp @Inject constructor(
    private val service: SpotifyService,
    private val databaseSongQuery: DatabaseSongQuery
) :
    SongListRepository {
    override fun getAllRecommendedSongs(): Flow<PagingData<Song>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SongPagingSource(service)
            }
        ).flow
    }

    override suspend fun downloadSong(songDb: SongDb) {
        databaseSongQuery.saveSongToDb(songDb)
    }
}