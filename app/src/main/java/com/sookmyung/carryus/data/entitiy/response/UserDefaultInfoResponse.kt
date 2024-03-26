package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.UserDefaultInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDefaultInfoResponse(
    @SerialName("memberName") val memberName: String,
    @SerialName("memberPhoneNumber") val memberPhoneNumber: String
) {
    fun toUserDefaultInfo(): UserDefaultInfo = UserDefaultInfo(
        memberName = this.memberName,
        memberPhoneNumber = this.memberPhoneNumber
    )
}
