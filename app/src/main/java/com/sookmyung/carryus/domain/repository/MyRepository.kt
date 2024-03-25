package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.domain.entity.MyProfile
import com.sookmyung.carryus.domain.entity.MyReviews

interface MyRepository {
    suspend fun getMyProfile(): Result<MyProfile>

    suspend fun getMyReviews(): Result<List<MyReviews>>
}
