package com.sookmyung.carryus.data.source

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.request.ReissueRequest
import com.sookmyung.carryus.data.entitiy.response.LoginResponse
import com.sookmyung.carryus.data.entitiy.response.ReissueResponse
import com.sookmyung.carryus.data.service.RefreshTokenService
import javax.inject.Inject

class RefreshTokenDataSource@Inject constructor(
    private val refreshTokenService: RefreshTokenService
) {
    suspend fun reissueToken(accessToken: String, refreshToken: String): BaseResponse<ReissueResponse> =
        refreshTokenService.reissueToken(ReissueRequest(accessToken, refreshToken))
}
