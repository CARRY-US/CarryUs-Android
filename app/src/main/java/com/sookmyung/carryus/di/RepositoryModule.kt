package com.sookmyung.carryus.di

import com.sookmyung.carryus.data.repositoryImpl.MainRepositoryImpl
import com.sookmyung.carryus.data.repositoryImpl.ReservationsRepositoryImpl
import com.sookmyung.carryus.domain.repository.MainRepository
import com.sookmyung.carryus.domain.repository.ReservationsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindToMainRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository

    @Binds
    @Singleton
    abstract fun bindToReservationsRepository(
        reservationsRepositoryImpl: ReservationsRepositoryImpl
    ): ReservationsRepository
}
