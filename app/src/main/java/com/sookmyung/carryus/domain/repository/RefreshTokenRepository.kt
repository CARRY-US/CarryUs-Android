package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.domain.entity.Token

interface RefreshTokenRepository {
    suspend fun reissueToken(): Result<Token>
    fun setAccessToken(accessToken: String)
}
