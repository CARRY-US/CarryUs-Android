package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.domain.entity.UserDefaultInfo

interface ReservationRepository {
    suspend fun getUserDefaultInfo(): Result<UserDefaultInfo>
}
