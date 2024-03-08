package com.sparklead.swipefy.di

import com.google.firebase.auth.FirebaseAuth
import com.sparklead.swipefy.data.repository.AuthRepositoryImp
import com.sparklead.swipefy.data.repository.SongListRepositoryImp
import com.sparklead.swipefy.data.repository.TrackRepositoryImp
import com.sparklead.swipefy.data.service.SpotifyService
import com.sparklead.swipefy.domain.repository.AuthRepository
import com.sparklead.swipefy.domain.repository.SongListRepository
import com.sparklead.swipefy.domain.repository.TrackRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesAuthRepositoryImp(firebaseAuth: FirebaseAuth): AuthRepository =
        AuthRepositoryImp(firebaseAuth)

    @Provides
    @Singleton
    fun providesTrackRepositoryImp(service: SpotifyService): TrackRepository =
        TrackRepositoryImp(service)

    @Provides
    @Singleton
    fun providesSongListRepository(service: SpotifyService): SongListRepository =
        SongListRepositoryImp(service)

}