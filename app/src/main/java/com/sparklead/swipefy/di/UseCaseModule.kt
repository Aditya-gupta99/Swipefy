package com.sparklead.swipefy.di

import com.sparklead.swipefy.domain.use_case.ConfirmPasswordValidationUseCase
import com.sparklead.swipefy.domain.use_case.EmailValidationUseCase
import com.sparklead.swipefy.domain.use_case.NameValidationUseCase
import com.sparklead.swipefy.domain.use_case.PasswordValidationUseCase
import com.sparklead.swipefy.domain.use_case.TrackUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesNameValidationUseCase(): NameValidationUseCase = NameValidationUseCase()


    @Provides
    @Singleton
    fun providesEmailValidationUseCase(): EmailValidationUseCase = EmailValidationUseCase()

    @Provides
    @Singleton
    fun providesPasswordValidationUseCase(): PasswordValidationUseCase = PasswordValidationUseCase()

    @Provides
    @Singleton
    fun providesConfirmPasswordValidationUseCase(): ConfirmPasswordValidationUseCase =
        ConfirmPasswordValidationUseCase()

}