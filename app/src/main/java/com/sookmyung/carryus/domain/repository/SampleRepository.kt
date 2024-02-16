package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.domain.entity.Sample

interface SampleRepository {
    suspend fun sample(name: String): Result<Sample>
}
