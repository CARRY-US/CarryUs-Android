package com.sookmyung.carryus.data.service

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.request.LoginRequest
import com.sookmyung.carryus.data.entitiy.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("auth/social/login")
    suspend fun postLogin(
        @Header("Platform-token") token: String,
        @Body body: LoginRequest
    ): BaseResponse<LoginResponse>
}
