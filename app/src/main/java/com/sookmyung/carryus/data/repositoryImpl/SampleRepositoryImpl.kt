package com.sookmyung.carryus.data.repositoryImpl

import com.sookmyung.carryus.data.source.SampleDataSource
import com.sookmyung.carryus.domain.entity.Sample
import com.sookmyung.carryus.domain.repository.SampleRepository
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(
    private val sampleDataSource: SampleDataSource
) : SampleRepository {
    override suspend fun sample(name: String): Result<Sample> {
        TODO("Not yet implemented")
    }
}
