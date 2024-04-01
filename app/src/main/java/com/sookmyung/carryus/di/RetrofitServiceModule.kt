package com.sookmyung.carryus.di

import com.sookmyung.carryus.data.service.AuthService
import com.sookmyung.carryus.data.service.MainService
import com.sookmyung.carryus.data.service.MyService
import com.sookmyung.carryus.data.service.ReservationsService
import com.sookmyung.carryus.data.service.ReservationService
import com.sookmyung.carryus.data.service.ReviewsService
import com.sookmyung.carryus.data.service.SearchService
import com.sookmyung.carryus.data.service.StoresService
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

    @Provides
    fun providesReservationsService(@RetrofitModule.CarryUsType retrofit: Retrofit): ReservationsService =
        retrofit.create(ReservationsService::class.java)

    @Provides
    fun providesSearchService(@RetrofitModule.CarryUsType retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)

    @Provides
    fun providesMyService(@RetrofitModule.CarryUsType retrofit: Retrofit): MyService =
        retrofit.create(MyService::class.java)

    @Provides
    fun providesStoresService(@RetrofitModule.CarryUsType retrofit: Retrofit): StoresService =
        retrofit.create(StoresService::class.java)

    @Provides
    fun providesReservationService(@RetrofitModule.CarryUsType retrofit: Retrofit): ReservationService =
        retrofit.create(ReservationService::class.java)

    @Provides
    fun providesReviewsService(@RetrofitModule.CarryUsType retrofit: Retrofit): ReviewsService =
        retrofit.create(ReviewsService::class.java)

    @Provides
    fun providesAuthService(@RetrofitModule.CarryUsType retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}
