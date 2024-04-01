package com.sookmyung.carryus.domain.entity

data class Token(
    val accessToken: String = "",
    val refreshToken: String = ""
)
