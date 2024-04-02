package com.sookmyung.carryus.data.service

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.request.ReissueRequest
import com.sookmyung.carryus.data.entitiy.response.ReissueResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshTokenService {
    @POST("auth/reissue")
    suspend fun reissueToken(
        @Body body: ReissueRequest
    ): BaseResponse<ReissueResponse>
}
