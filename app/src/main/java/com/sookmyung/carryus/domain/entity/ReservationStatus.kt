package com.sookmyung.carryus.domain.entity

enum class ReservationStatus(val status: String) {
    ACCEPTED("수락"),
    CANCELED("취소"),
    WAITING("대기"),
    COMPLETED("완료");
}
