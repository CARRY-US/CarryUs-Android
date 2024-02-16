package com.sookmyung.carryus.data.source

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.request.SampleRequest
import com.sookmyung.carryus.data.entitiy.response.SampleResponse
import com.sookmyung.carryus.data.service.SampleService
import javax.inject.Inject

class SampleDataSource @Inject constructor(
    private val sampleService: SampleService
) {
    suspend fun sample(name: String): BaseResponse<SampleResponse> =
        sampleService.sample(SampleRequest(name))
}
