package com.sookmyung.carryus.di

import com.sookmyung.carryus.data.repositoryImpl.AuthRepositoryImpl
import com.sookmyung.carryus.data.repositoryImpl.MainRepositoryImpl
import com.sookmyung.carryus.data.repositoryImpl.MyRepositoryImpl
import com.sookmyung.carryus.data.repositoryImpl.RefreshTokenRepositoryImpl
import com.sookmyung.carryus.domain.repository.MyRepository
import com.sookmyung.carryus.data.repositoryImpl.ReservationsRepositoryImpl
import com.sookmyung.carryus.domain.repository.ReservationsRepository
import com.sookmyung.carryus.data.repositoryImpl.ReservationRepositoryImpl
import com.sookmyung.carryus.data.repositoryImpl.ReviewsRepositoryImpl
import com.sookmyung.carryus.data.repositoryImpl.SearchRepositoryImpl
import com.sookmyung.carryus.data.repositoryImpl.StoresRepositoryImpl
import com.sookmyung.carryus.domain.repository.AuthRepository
import com.sookmyung.carryus.domain.repository.MainRepository
import com.sookmyung.carryus.domain.repository.RefreshTokenRepository
import com.sookmyung.carryus.domain.repository.ReservationRepository
import com.sookmyung.carryus.domain.repository.ReviewsRepository
import com.sookmyung.carryus.domain.repository.SearchRepository
import com.sookmyung.carryus.domain.repository.StoresRepository
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

    @Binds
    @Singleton
    abstract fun bindToSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository

    @Binds
    @Singleton
    abstract fun bindToMyRepository(
        myRepositoryImpl: MyRepositoryImpl
    ): MyRepository

    @Binds
    @Singleton
    abstract fun bindToStoresRepository(
        storesRepositoryImpl: StoresRepositoryImpl
    ): StoresRepository

    @Binds
    @Singleton
    abstract fun bindToReservationRepository(
        reservationRepositoryImpl: ReservationRepositoryImpl
    ): ReservationRepository

    @Binds
    @Singleton
    abstract fun bindToReviewsRepository(
        reviewsRepositoryImpl: ReviewsRepositoryImpl
    ): ReviewsRepository

    @Binds
    @Singleton
    abstract fun bindToAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindRefreshRepository(
        refreshTokenRepositoryImpl: RefreshTokenRepositoryImpl
    ): RefreshTokenRepository
}
