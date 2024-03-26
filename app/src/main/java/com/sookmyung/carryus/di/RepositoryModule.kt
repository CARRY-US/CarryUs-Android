package com.sookmyung.carryus.di

import com.sookmyung.carryus.data.repositoryImpl.MainRepositoryImpl
import com.sookmyung.carryus.data.repositoryImpl.MyRepositoryImpl
import com.sookmyung.carryus.data.repositoryImpl.SearchRepositoryImpl
import com.sookmyung.carryus.data.repositoryImpl.StoresRepositoryImpl
import com.sookmyung.carryus.domain.repository.MainRepository
import com.sookmyung.carryus.domain.repository.MyRepository
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

}
