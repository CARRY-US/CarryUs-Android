package com.sookmyung.carryus.data.repositoryImpl

import com.sookmyung.carryus.data.source.StoresDataSource
import com.sookmyung.carryus.domain.entity.ReservationId
import com.sookmyung.carryus.domain.entity.StoreDetail
import com.sookmyung.carryus.domain.entity.StoreDetailReview
import com.sookmyung.carryus.domain.entity.StoreReservationTime
import com.sookmyung.carryus.domain.repository.StoresRepository
import javax.inject.Inject

class StoresRepositoryImpl @Inject constructor(
    private val storesDataSource: StoresDataSource
) : StoresRepository {
    override suspend fun getStoreDetailInfo(storeId: Int): Result<StoreDetail> = runCatching {
        storesDataSource.getStoreDetailInfo(storeId)
    }.mapCatching { response ->
        response.data?.toStoreDetail() ?: StoreDetail()
    }

    override suspend fun getStoreDetailReview(storeId: Int): Result<StoreDetailReview> =
        runCatching {
            storesDataSource.getStoreDetailReview(storeId)
        }.mapCatching { response ->
            response.data?.toStoreDetailReview() ?: StoreDetailReview()
        }

    override suspend fun getStoreReservationTime(
        storeId: Int,
        date: String,
        extraSmallCount: Int,
        smallCount: Int,
        largeCount: Int,
        extraLargeCount: Int
    ): Result<StoreReservationTime> = runCatching {
        storesDataSource.getStoreReservationTime(
            storeId,
            date,
            extraSmallCount,
            smallCount,
            largeCount,
            extraLargeCount
        )
    }.mapCatching { response ->
        response.data?.toStoreReservationTime() ?: StoreReservationTime()
    }

    override suspend fun postStoreReservationRequest(
        storeId: Int,
        extraSmallCount: Int,
        smallCount: Int,
        largeCount: Int,
        extraLargeCount: Int,
        reservationStartTime: String,
        reservationEndTime: String,
        memberName: String,
        memberPhoneNumber: String,
        memberRequestContent: String
    ): Result<ReservationId> = runCatching {
        storesDataSource.postStoreReservationRequest(
            storeId,
            extraSmallCount,
            smallCount,
            largeCount,
            extraLargeCount,
            reservationStartTime,
            reservationEndTime,
            memberName,
            memberPhoneNumber,
            memberRequestContent
        )
    }.mapCatching { response -> response.data?.toReservationId() ?: ReservationId() }


}
