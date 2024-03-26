package com.sookmyung.carryus.domain.entity

data class Time(
    val timeId: Int = 0,
    val hour: String = "00",
    val minute: String = "00",
    val available: Boolean = false,
    val select: Boolean = false
)
