package com.sparklead.swipefy.data.repository

import com.sparklead.core.data.model.Song
import com.sparklead.core.data.model_db.SongDb
import com.sparklead.datastore.database.DatabaseSongQuery
import com.sparklead.swipefy.domain.repository.DownloadSongRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DownloadSongRepositoryImp @Inject constructor(private val databaseSongQuery: DatabaseSongQuery) :
    DownloadSongRepository {

    override fun getAllDownloadSongs(): Flow<List<Song>> {
        return databaseSongQuery.getSongFromDb()
    }

}