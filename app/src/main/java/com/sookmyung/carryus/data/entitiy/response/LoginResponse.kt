package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.Token
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("memberId")
    val memberId: Int,
    @SerialName("type")
    val type: String,
    @SerialName("role")
    val role: String,
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String
) {
    fun toToken(): Token = Token(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )
}
