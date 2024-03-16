package com.sookmyung.carryus.domain.entity

enum class ReservationStatus(val status: String) {
    ACCEPTED("수락"),
    CANCELED("취소"),
    WAITING("대기"),
    COMPLETED("완료");
    companion object {
        const val ACCEPTED_STRING = "승인완료"
        const val CANCELED_STRING = "취소완료"
        const val WAITING_STRING = "대기중"
        const val COMPLETED_STRING = "보관완료"
    }
}
