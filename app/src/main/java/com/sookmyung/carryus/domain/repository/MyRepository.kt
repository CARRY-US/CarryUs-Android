package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.domain.entity.MyProfile

interface MyRepository {
    suspend fun getMyProfile(): Result<MyProfile>
}
