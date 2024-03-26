package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.MyProfile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyProfileResponse (
    @SerialName("memberName")
    val memberName: String,
    @SerialName("memberProfileImg")
    val memberProfileImg: String
){
    fun toMyProfile(): MyProfile = MyProfile(
        memberName = this.memberName,
        memberProfileImg = this.memberProfileImg
    )
}
