package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.domain.entity.Token

interface AuthRepository {
    suspend fun postLogin(token: String, platformType: String, role: String): Result<Token>
    fun initToken(accessToken: String, refreshToken: String)
    fun getSignedUp(): Boolean
    fun initSignUpState(isSignUpState: Boolean)
}
