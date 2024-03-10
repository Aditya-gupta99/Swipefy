package com.sparklead.datastore.di

import com.sparklead.datastore.model.ArtistDb
import com.sparklead.datastore.model.SongDb
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    fun providesRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                SongDb::class,
                ArtistDb::class
            )
        ).compactOnLaunch().build()
        return Realm.open(config)
    }
}