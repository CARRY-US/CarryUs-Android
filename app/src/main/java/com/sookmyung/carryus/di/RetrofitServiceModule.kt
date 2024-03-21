package com.sookmyung.carryus.di

import com.sookmyung.carryus.data.service.MainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {
    @Provides
    fun providesMainService(@RetrofitModule.CarryUsType retrofit: Retrofit): MainService =
        retrofit.create(MainService::class.java)
}
