package com.sparklead.datastore.database

import com.sparklead.core.data.mapper.toSong
import com.sparklead.core.data.model.Song
import com.sparklead.core.data.model_db.SongDb
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseSongQuery @Inject constructor(private val realm: Realm) {

    suspend fun saveSongToDb(songDb: SongDb) {
        realm.write {
            copyToRealm(songDb)
        }
    }

    fun getSongFromDb(): Flow<List<Song>> {
        return realm.query<SongDb>().asFlow()
            .map { it.list.map { clientDb -> clientDb.toSong() }.toList() }
    }

}