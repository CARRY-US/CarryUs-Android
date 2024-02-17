package com.sookmyung.carryus.data.service // ktlint-disable filename

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.request.SampleRequest
import com.sookmyung.carryus.data.entitiy.response.SampleResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SampleService {
    @POST
    suspend fun sample(
        @Body body: SampleRequest
    ): BaseResponse<SampleResponse>
}
