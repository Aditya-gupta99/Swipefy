package com.sparklead.swipefy.di

import com.google.firebase.auth.FirebaseAuth
import com.sparklead.swipefy.data.repository.AuthRepositoryImp
import com.sparklead.swipefy.domain.repository.AuthRepository
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

}