package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.BaggageTypeInfo
import com.sookmyung.carryus.domain.entity.StoreDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreDetailInfoResponse(
    @SerialName("storeId") val storeId: Int,
    @SerialName("storeImgUrl") val storeImgUrl: String,
    @SerialName("storeName") val storeName: String,
    @SerialName("storeLocation") val storeLocation: String,
    @SerialName("closedDay") val closedDay: String,
    @SerialName("openingHours") val openingHours: String,
    @SerialName("storePhoneNumber") val storePhoneNumber: String,
    @SerialName("baggageTypeInfoList") val baggageTypeInfoList: List<Baggage>
) {
    @Serializable
    data class Baggage(
        @SerialName("baggageType") val baggageType: String,
        @SerialName("baggageCount") val baggageCount: Int,
        @SerialName("baggagePrice") val baggagePrice: Int,
    ) {
        fun toBaggageType(): BaggageTypeInfo = BaggageTypeInfo(
            baggageType = this.baggageType,
            baggageCount = this.baggageCount,
            baggagePrice = this.baggagePrice
        )
    }

    fun toStoreDetail(): StoreDetail = StoreDetail(
        storeId = this.storeId,
        storeImgUrl = this.storeImgUrl,
        storeName = this.storeName,
        storeLocation = this.storeLocation,
        closedDay = this.closedDay,
        openingHours = this.openingHours,
        storePhoneNumber = this.storePhoneNumber,
        baggageTypeInfoList = baggageTypeInfoList.map { it.toBaggageType() }
    )
}
