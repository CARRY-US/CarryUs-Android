package com.sookmyung.carryus.data.entitiy.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val platformType: String,
    val role: String
)
