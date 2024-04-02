package com.sookmyung.carryus.data.entitiy.request

import kotlinx.serialization.Serializable

@Serializable
data class ReissueRequest (
    val accessToken: String,
    val refreshToken: String
)
