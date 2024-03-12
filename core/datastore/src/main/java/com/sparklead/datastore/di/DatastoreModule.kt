package com.sparklead.datastore.di

import com.sparklead.core.data.model_db.ArtistDb
import com.sparklead.core.data.model_db.SongDb
import com.sparklead.core.data.model_db.UserDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Provides
    fun providesRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                SongDb::class,
                ArtistDb::class,
                UserDb::class
            )
        ).compactOnLaunch().build()
        return Realm.open(config)
    }
}