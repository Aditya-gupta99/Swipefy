package com.sparklead.swipefy.domain.repository

import com.sparklead.core.data.model.Song
import com.sparklead.core.data.model_db.SongDb
import kotlinx.coroutines.flow.Flow

interface DownloadSongRepository {

    fun getAllDownloadSongs(): Flow<List<Song>>

}