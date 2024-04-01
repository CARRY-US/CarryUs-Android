package com.sookmyung.carryus.data.entitiy.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("platformType")
    val platformType: String,
    @SerialName("role")
    val role: String
)
