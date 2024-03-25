package com.sookmyung.carryus.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class MyProfile(
    val memberName: String,
    val memberProfileImg: String
)
