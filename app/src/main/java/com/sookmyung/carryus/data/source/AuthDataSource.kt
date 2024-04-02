package com.sookmyung.carryus.data.source

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.request.LoginRequest
import com.sookmyung.carryus.data.entitiy.response.LoginResponse
import com.sookmyung.carryus.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService
) {
    suspend fun postLogin(token:String, platformType: String, role: String): BaseResponse<LoginResponse> =
        authService.postLogin(token, LoginRequest(platformType, role))
}
